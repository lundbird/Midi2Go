package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;

import java.lang.NumberFormatException;
import java.util.ArrayList;

@DesignerComponent(version = YaVersion.LABEL_COMPONENT_VERSION,
  description = "This provides some basic vector arithmetic of 2 vectors.",
  category = ComponentCategory.EXTERNAL,
  nonVisible = true,
  iconName = "images/externalComponent.png")
@SimpleObject
public final class VectorArithmetic extends AndroidNonvisibleComponent {
  private String v1 = "";
  private String v2 = "";

  public VectorArithmetic(ComponentContainer container) {
    super(container.$form());
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
  @SimpleProperty(description = "The entries of a vector as a string of comma-separated numbers")
  public void Vector1(String v1) {
    this.v1 = v1;
  }

  @SimpleProperty
  public String Vector1() {
    return v1;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
  @SimpleProperty(description = "The entries of a vector as a string of comma-separated numbers")
  public void Vector2(String v2) {
    this.v2 = v2;
  }

  @SimpleProperty
  public String Vector2() {
    return v2;
  }

  @SimpleFunction(description = "Vector addition of Vector1 and Vector2")
  public String Add() {
    ArrayList<Double> entries1 = new ArrayList<Double>();
    ArrayList<Double> entries2 = new ArrayList<Double>();

    for (String entry : v1.split(",")) {
      try {
        entries1.add(Double.parseDouble(entry.trim()));
      } catch (NumberFormatException exception) {
        throw new YailRuntimeError("Vector1 contains invalid entries", "Invalid number");
      }
    }

    for (String entry : v2.split(",")) {
      try {
        entries2.add(Double.parseDouble(entry.trim()));
      } catch (NumberFormatException exception) {
        throw new YailRuntimeError("Vector2 contains invalid entries", "Invalid number");
      }
    }

    if (entries1.size() != entries2.size()) {
      throw new YailRuntimeError("Vector1 and Vector 2 should have the same number of entries", "Size mis-match");
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < entries1.size(); ++i) {
      Double sum = entries1.get(i) + entries2.get(i);
      if (i < entries1.size() - 1) {
        result.append(sum.toString() + ",");
      } else {
        result.append(sum.toString());
      }
    }
    return result.toString();
  }
}
