/**
 * @(#)EditCanvasPanel.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.gui.action;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.text.*;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_COLOR;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_OPACITY;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.event.DrawingAttributeEditorHandler;
import org.jhotdraw.draw.gui.JAttributeSlider;
import org.jhotdraw.draw.gui.JAttributeTextField;
import org.jhotdraw.formatter.JavaNumberFormatter;
import org.jhotdraw.gui.Dialogs;
import org.jhotdraw.gui.JPopupButton;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * The EditCanvasPanel can be used to edit the attributes of a Drawing.
 *
 * @see org.jhotdraw.draw.Drawing
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EditCanvasPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private ResourceBundleUtil labels;
    private Drawing drawing;
    private DrawingEditor editor; // FIX: Added Editor field
    private JAttributeSlider opacitySlider;
    private JColorChooser colorChooser;
    private DrawingAttributeEditorHandler<Double> opacityFieldHandler;
    private DrawingAttributeEditorHandler<Double> opacitySliderHandler;

    // UI Components
    private javax.swing.JButton colorButton;
    private javax.swing.JLabel colorLabel;
    private org.jhotdraw.draw.gui.JAttributeTextField<Double> opacityField;
    private javax.swing.JLabel opacityLabel;
    private org.jhotdraw.gui.JPopupButton opacityPopupButton;

    /**
     * Creates new form.
     */
    public EditCanvasPanel() {
        labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        initComponents();

        // Initialize Slider
        opacitySlider = new JAttributeSlider(JSlider.VERTICAL, 0, 100, 100);
        opacityPopupButton.add(opacitySlider);
        opacityPopupButton.putClientProperty("JButton.buttonType", "toolbar");

        // Initialize Formatter for Opacity Field
        NumberFormatter nf = new NumberFormatter();
        nf.setMaximum(1d);
        nf.setMinimum(0d);
        opacityField.setFormatterFactory(JavaNumberFormatter.createFormatterFactory(0d, 1d, 100d));

        // Handlers will be fully initialized when setEditor is called
    }

    /**
     * FIX: Sets the editor and initializes handlers correctly.
     * This is the missing link that prevented the toolbox from working.
     */
    public void setEditor(DrawingEditor editor) {
        this.editor = editor;
        // Re-initialize handlers with the active editor
        opacityFieldHandler = new DrawingAttributeEditorHandler<>(CANVAS_FILL_OPACITY, opacityField, editor);
        opacitySliderHandler = new DrawingAttributeEditorHandler<>(CANVAS_FILL_OPACITY, opacitySlider, editor);
        updatePanel();
    }

    public void setDrawing(Drawing newValue) {
        drawing = newValue;
        updatePanel();
    }

    private void updatePanel() {
        if (drawing != null) {
            Color c = drawing.get(CANVAS_FILL_COLOR);
            colorButton.setBackground(c == null ? Color.WHITE : c);
        }
    }

    private JColorChooser getColorChooser() {
        if (colorChooser == null) {
            colorChooser = new JColorChooser();
        }
        return colorChooser;
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        colorLabel = new JLabel();
        colorButton = new JButton();
        opacityLabel = new JLabel();
        opacityField = new JAttributeTextField<Double>();
        opacityPopupButton = new JPopupButton();

        setLayout(new GridBagLayout());

        colorLabel.setText(labels.getString("attribute.canvasFillColor.text"));
        colorLabel.setToolTipText(labels.getString("attribute.backgroundColor.toolTipText"));
        add(colorLabel, new GridBagConstraints());

        colorButton.setText(" ");
        // Ensure button has a minimum size so it is visible even if empty
        colorButton.setPreferredSize(new java.awt.Dimension(24, 24));
        colorButton.setToolTipText(labels.getString("attribute.backgroundColor.toolTipText"));
        colorButton.putClientProperty("Quaqua.Button.style", "colorWell");
        colorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorButtonPerformed(evt);
            }
        });
        add(colorButton, new GridBagConstraints());

        opacityLabel.setIcon(new ImageIcon(getClass().getResource("/org/jhotdraw/draw/action/images/attributeOpacity.png")));
        opacityLabel.setToolTipText(labels.getString("attribute.opacity.toolTipText"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        add(opacityLabel, gridBagConstraints);

        opacityField.setColumns(3);
        add(opacityField, new GridBagConstraints());

        opacityPopupButton.setIcon(new ImageIcon(getClass().getResource("/org/jhotdraw/draw/action/images/popupIcon.png")));
        opacityPopupButton.setToolTipText(labels.getString("attribute.opacity.toolTipText"));
        add(opacityPopupButton, new GridBagConstraints());
    }

    private void colorButtonPerformed(java.awt.event.ActionEvent evt) {
        if (drawing != null) {
            Color initialColor = drawing.get(CANVAS_FILL_COLOR);
            if (initialColor == null) initialColor = Color.WHITE;

            Color color = Dialogs.showColorChooserDialog(getColorChooser(), this,
                    labels.getString("attribute.backgroundColor"),
                    initialColor);

            if (color != null) {
                colorButton.setBackground(color);
                // Directly update the drawing attribute
                drawing.willChange();
                drawing.set(CANVAS_FILL_COLOR, color);
                drawing.fireUndoableEditHappened(
                        CANVAS_FILL_COLOR.setUndoable(drawing, color));
                drawing.changed();
            }
        }
    }
}