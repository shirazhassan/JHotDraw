/*
 * @(#)CanvasToolBar.java
 *
 * Copyright (c) 2007-2008 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.svg.gui;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

import org.jhotdraw.draw.*;
import org.jhotdraw.draw.event.DrawingAttributeEditorHandler;
import org.jhotdraw.draw.gui.JAttributeSlider;
import org.jhotdraw.draw.gui.JAttributeTextField;
import org.jhotdraw.formatter.JavaNumberFormatter;
import org.jhotdraw.gui.JPopupButton;
import org.jhotdraw.gui.action.ButtonFactory;
import org.jhotdraw.gui.plaf.palette.*;
import org.jhotdraw.util.ResourceBundleUtil;

import static org.jhotdraw.draw.AttributeKeys.*;

public class CanvasToolBar extends AbstractToolBar {

    private static final long serialVersionUID = 1L;
    private ResourceBundleUtil labels;

    // Fields for handlers to maintain connection
    private DrawingAttributeEditorHandler<Color> colorHandler;
    private DrawingAttributeEditorHandler<Double> opacitySliderHandler;
    private DrawingAttributeEditorHandler<Double> opacityFieldHandler;

    public CanvasToolBar() {
        labels = ResourceBundleUtil.getBundle("org.jhotdraw.samples.svg.Labels");
        setName(labels.getString(getID() + ".toolbar"));
        setDisclosureStateCount(3);
    }

    @Override
    public void setEditor(DrawingEditor newValue) {
        DrawingEditor oldEditor = this.editor;
        super.setEditor(newValue);
        // Update handlers when editor is injected
        if (colorHandler != null) {
            colorHandler.setEditor(newValue);
        }
        if (opacitySliderHandler != null) {
            opacitySliderHandler.setEditor(newValue);
        }
        if (opacityFieldHandler != null) {
            opacityFieldHandler.setEditor(newValue);
        }
    }

    @Override
    protected JComponent createDisclosedComponent(int state) {
        JPanel p = null;
        switch (state) {
            case 1:
                p = new JPanel(new GridBagLayout());
                p.setOpaque(false);
                p.setBorder(new EmptyBorder(5, 5, 5, 8));
                JLabel title = new JLabel(labels.getString("attribute.canvas.text"));
                title.setUI((PaletteLabelUI) PaletteLabelUI.createUI(title));
                p.add(title);
                break;
            case 2:
                p = new JPanel(new GridBagLayout());
                p.setOpaque(false);
                p.setBorder(new EmptyBorder(5, 5, 5, 8));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;

                // --- Background Color Button ---
                // FIX: Use ButtonFactory to create the button correctly for the editor
                Map<AttributeKey<?>, Object> defaultAttributes = new HashMap<AttributeKey<?>, Object>();
                defaultAttributes.put(CANVAS_FILL_COLOR, Color.WHITE);

                // createDrawingColorButton is the correct way to get a functional color button
                // --- Background Color Button ---
                defaultAttributes = new HashMap<AttributeKey<?>, Object>();
                defaultAttributes.put(CANVAS_FILL_COLOR, Color.BLACK);

// We need to provide the color list and column count to satisfy ButtonFactory version
                AbstractButton colorButton = ButtonFactory.createDrawingColorButton(
                        editor,
                        CANVAS_FILL_COLOR,
                        ButtonFactory.HSB_COLORS,  // Color list
                        ButtonFactory.HSB_COLORS_COLUMN_COUNT, // Column count
                        "attribute.canvasFillColor",
                        labels,
                        defaultAttributes
                );

                colorButton.setUI((PaletteButtonUI) PaletteButtonUI.createUI(colorButton));
                colorButton.setPreferredSize(new Dimension(24, 24));
                p.add(colorButton, gbc);

                // --- Opacity Slider ---
                JAttributeSlider opacitySlider = new JAttributeSlider(JSlider.VERTICAL, 0, 100, 100);
                opacitySlider.setUI((javax.swing.plaf.SliderUI) PaletteSliderUI.createUI(opacitySlider));
                opacitySlider.setScaleFactor(100d);

                JPopupButton opacityPopupButton = new JPopupButton();
                opacityPopupButton.add(opacitySlider);
                opacityPopupButton.setUI((PaletteButtonUI) PaletteButtonUI.createUI(opacityPopupButton));
                opacityPopupButton.setIcon(new ImageIcon(getClass().getResource("/org/jhotdraw/draw/action/images/attributeOpacity.png")));

                // Initialize Slider Handler
                opacitySliderHandler = new DrawingAttributeEditorHandler<Double>(CANVAS_FILL_OPACITY, opacitySlider, editor);

                gbc.gridx = 1;
                gbc.insets = new Insets(0, 3, 0, 0);
                p.add(opacityPopupButton, gbc);

                // ---Opacity Field ---
                JAttributeTextField<Double> opacityField = new JAttributeTextField<Double>();
                opacityField.setColumns(3);
                opacityField.setUI((PaletteFormattedTextFieldUI) PaletteFormattedTextFieldUI.createUI(opacityField));
                opacityField.setFormatterFactory(JavaNumberFormatter.createFormatterFactory(0d, 1d, 100d));

                // Initialize Field Handler
                opacityFieldHandler = new DrawingAttributeEditorHandler<Double>(CANVAS_FILL_OPACITY, opacityField, editor);

                gbc.gridx = 2;
                p.add(opacityField, gbc);
                break;
        }
        return p;
    }

    @Override
    protected String getID() {
        return "canvas";
    }
}