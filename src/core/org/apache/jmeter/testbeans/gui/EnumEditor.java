/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jmeter.testbeans.gui;

import java.awt.Component;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditorSupport;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.apache.jmeter.gui.ClearGui;

/**
 * This class implements a property editor for String properties based on an enum
 * that supports custom editing (i.e.: provides a GUI component) based on a
 * combo box.
 * <p>
 * The provided GUI is a combo box with an option for each value in the enum.
 * <p>
 * The resource bundle must be provided by the attribute
 * GenericTestBeanCustomizer.RESOURCE_BUNDLE
 */
class EnumEditor extends PropertyEditorSupport implements ClearGui {

    private final JComboBox combo;

    private final DefaultComboBoxModel model;

    private final Enum<?>[] eNum;

    private final ResourceBundle rb;

    private final int defaultIndex;

    public EnumEditor(PropertyDescriptor descriptor) {
        model = new DefaultComboBoxModel();
        combo = new JComboBox(model);
        combo.setEditable(false);
        rb = (ResourceBundle) descriptor.getValue(GenericTestBeanCustomizer.RESOURCE_BUNDLE);
        eNum = (Enum<?>[]) descriptor.getValue(GenericTestBeanCustomizer.TAGS);
        for(Enum<?> e : eNum) {
            model.addElement(rb.getObject(e.toString()));
        }
        Enum<?> def = (Enum<?>) descriptor.getValue(GenericTestBeanCustomizer.DEFAULT);
        if (def != null) {
            this.defaultIndex = def.ordinal();
        } else {
            this.defaultIndex = 0;
        }
        combo.setSelectedIndex(defaultIndex);
    }

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return combo;
    }

    @Override
    public Object getValue() {
        return Integer.valueOf(combo.getSelectedIndex());
    }

    @Override
    public String getAsText() {
        Object value = combo.getSelectedItem();
        return (String) value;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Enum<?>){
            combo.setSelectedIndex(((Enum<?>) value).ordinal());
        } else if (value instanceof Integer) {
            combo.setSelectedIndex(((Integer) value).intValue());
        } else {
            combo.setSelectedItem(value);
        }
    }

    @Override
    public void setAsText(String value) {
        combo.setSelectedItem(value);
    }

    @Override
    public void clearGui() {
        combo.setSelectedIndex(defaultIndex);
    }

}