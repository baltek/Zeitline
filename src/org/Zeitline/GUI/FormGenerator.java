package org.Zeitline.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class FormGenerator
        implements IFormGenerator, Serializable {

    public FormGenerator() {}

    public JPanel createForm(JLabel[] labels){
        List<IFormItem> items = new ArrayList<>(labels.length);
        for (JLabel label: labels){
            items.add(getFormItem(label.getText(), label));
        }

        return createForm(items);
    }

    private JPanel createForm(List<IFormItem> items) {

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int ypos = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);


        //for (Enumeration itemList = items.elements(); itemList.hasMoreElements(); ) {
        for (IFormItem item: items) {
            c.gridx = 0;
            c.gridy = ypos;
            c.anchor = GridBagConstraints.PAGE_START;
            JLabel label = new JLabel(item.getLabelText(), JLabel.TRAILING);
            contentPanel.add(label, c);

            // TODO: consider c.weight
            c.gridx = 1;
            c.anchor = GridBagConstraints.CENTER;
            Component comp = item.getComponent();
            label.setLabelFor(comp);
            // TODO consider flag for scrollbar
            contentPanel.add(comp, c);
            ypos++;
        }

        return contentPanel;
    }

    @Override
    public IFormItem getFormItem(String label, Component component) {
        return new FormItem(label, component);
    }

}
