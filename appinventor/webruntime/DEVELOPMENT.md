# Development process for GWT Android emulation

## Preliminaries

In addition to a standard App Inventor build, you will need the Gambit
Scheme compiler. On macOS with Homebrew set up, you can install it by
running:

    brew install gambitscheme

For other platforms, please see the [Gambit Scheme website](https://gambitscheme.org).

## Building

In your terminal, cd into the webruntime directory and run `ant`

This builds two targets:

- webruntime: The web runtime provides a copy of runtime.scm
  specifically targetting the web environment to be compiled with
  Gambit Scheme. See [Preliminaries](#Preliminaries) about setting up
  Gambit Scheme. Gambit will produce a JS version of the runtime that
  interacts with the components via shared objects on the browser
  `window` object.
- webemu: The web emulation library of Android components. It also
  contains implementations of external packages such as android,
  androidx, java, javax, etc.

## Bring over a component from the Android version

The general steps for adding support for a component in the web
emulator are as follows:

1. Open components/build.xml and look for the AiComponentsGwt target.
2. Add the path to the Java file for the component sorted
   lexicographically into the existing list.
3. Open webruntime/src/edu/mit/appinventor/webemu/ComponentFactory.java.
4. Add an import for the component and then add an entry in the LOOKUP
   map.
5. Go through the component's Java file and annotate anything with
   @SimpleProperty with @JsProperty(name = "Name") and @SimpleFunction
   with @JsMethod(name = "Name"). This ensures that GWT will not
   mangle the name so it is accessible from JavaScript when running in
   the browser.

**Note:** We are trying to figure out a better way of handling this.

At this point, try building the webruntime system following the
instructions in [Building](#Building).

If errors are encountered:

- For errors referencing files under the com.google.appinventor package:
    - If the Java file for the class is already defined, you may need
      to either copy the missing implementation over from the real
      Java file in the components module, or mock out the
      function. Generally, we prefer copying to reimplementing using
      native functionality unless absolutely required.
    - If the Java file is missing, try to copy it over. This is
      required for classes/interfaces in the type hierarchy of the
      component. For utility classes, you have a lot of leeway about
      what to do. Sometimes copying them wholesale is fine. Otherwise,
      you can copy functions in chunks, or reimplement the entire
      utility class through JSNI.
    - In some cases, it may make sense to refactor the component code
      to make mocking easier. For an example, see commit eaf7955e.
- For errors referencing files in android/androidx packages:
    - Sometimes, if the class isn't overly complicated you can copy
      the sources from the Android Open Source Project (install the
      sources via the Android SDK)
    - Otherwise, it can make sense to mock out the Android API.
    - In either case, the class files are put into
      webruntime/src/android or webruntime/src/androidx
- For errors referencing the java/javax packages:
    - These need to be mocked out in a special way using GWT's
      supersrc feature. The implementation go under
      webruntime/src/com/google/gwt/jreemul. Note that the package in
      the file must be the original java/javax package, *not* the
      com.google.gwt... package.
    - Generally, for this level of complexity talk to Evan if you are
      unsure how to proceed.
