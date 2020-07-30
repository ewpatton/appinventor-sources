// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2020 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.annotations;

import com.google.appinventor.components.common.OptionList;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a parameter/return type as accepting an enum value. This should *only* be
 * used to upgrade old components. Eg, setters that currently accept concrete types like int:
 * 
 * @SimpleProperty
 * public void AlignHorizontal (@Options(HorizontalAlignment.class) int alignment) { }
 * 
 * New components that want to accept or return an enum should just use that enum type as the
 * parameter type. E.g:
 * 
 * @SimpleProperty
 * public void CurrentSeason (Season season) { }
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface Options {
    public Class<? extends OptionList> value();
}
