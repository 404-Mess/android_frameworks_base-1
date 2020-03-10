/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.view.inputmethod;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.SuppressLint;
import android.annotation.TestApi;
import android.os.Parcelable;
import android.view.inline.InlinePresentationSpec;

import com.android.internal.util.DataClass;

/**
 * This class represents the description of an inline suggestion. It contains information to help
 * the IME to decide where and when to show the suggestions. See {@link InlineSuggestion} for more
 * information.
 */
@DataClass(
        genEqualsHashCode = true,
        genToString = true,
        genHiddenConstDefs = true,
        genHiddenConstructor = true)
public final class InlineSuggestionInfo implements Parcelable {

    /**
     * Suggestion source: the suggestion is made by the user selected autofill service.
     */
    public static final @Source String SOURCE_AUTOFILL = "android:autofill";
    /**
     * Suggestion source: the suggestion is made by the platform.
     */
    public static final @Source String SOURCE_PLATFORM = "android:platform";

    /**
     * UI type: the UI contains an Autofill suggestion that will autofill the fields when tapped.
     */
    public static final @Type String TYPE_SUGGESTION = "android:autofill:suggestion";

    /**
     * UI type: the UI contains an widget that will launch an intent when tapped.
     */
    @SuppressLint({"IntentName"})
    public static final @Type String TYPE_ACTION = "android:autofill:action";

    /** The presentation spec to which the inflated suggestion view abides. */
    private final @NonNull InlinePresentationSpec mPresentationSpec;

    /** The source from which the suggestion is provided. */
    private final @NonNull @Source String mSource;

    /** Hints for the type of data being suggested. */
    private final @Nullable String[] mAutofillHints;

    /** The type of the UI. */
    private final @NonNull @Type String mType;

    /** Whether the suggestion should be pinned or not. */
    private final boolean mPinned;

    /**
     * Creates a new {@link InlineSuggestionInfo}, for testing purpose.
     *
     * @hide
     */
    @TestApi
    @NonNull
    public static InlineSuggestionInfo newInlineSuggestionInfo(
            @NonNull InlinePresentationSpec presentationSpec,
            @NonNull @Source String source,
            @Nullable String[] autofillHints, @NonNull @Type String type, boolean isPinned) {
        return new InlineSuggestionInfo(presentationSpec, source, autofillHints, type, isPinned);
    }



    // Code below generated by codegen v1.0.14.
    //
    // DO NOT MODIFY!
    // CHECKSTYLE:OFF Generated code
    //
    // To regenerate run:
    // $ codegen $ANDROID_BUILD_TOP/frameworks/base/core/java/android/view/inputmethod/InlineSuggestionInfo.java
    //
    // To exclude the generated code from IntelliJ auto-formatting enable (one-time):
    //   Settings > Editor > Code Style > Formatter Control
    //@formatter:off


    /** @hide */
    @android.annotation.StringDef(prefix = "SOURCE_", value = {
        SOURCE_AUTOFILL,
        SOURCE_PLATFORM
    })
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.SOURCE)
    @DataClass.Generated.Member
    public @interface Source {}

    /** @hide */
    @android.annotation.StringDef(prefix = "TYPE_", value = {
        TYPE_SUGGESTION,
        TYPE_ACTION
    })
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.SOURCE)
    @DataClass.Generated.Member
    public @interface Type {}

    /**
     * Creates a new InlineSuggestionInfo.
     *
     * @param presentationSpec
     *   The presentation spec to which the inflated suggestion view abides.
     * @param source
     *   The source from which the suggestion is provided.
     * @param autofillHints
     *   Hints for the type of data being suggested.
     * @param type
     *   The type of the UI.
     * @param pinned
     *   Whether the suggestion should be pinned or not.
     * @hide
     */
    @DataClass.Generated.Member
    public InlineSuggestionInfo(
            @NonNull InlinePresentationSpec presentationSpec,
            @NonNull @Source String source,
            @Nullable String[] autofillHints,
            @NonNull @Type String type,
            boolean pinned) {
        this.mPresentationSpec = presentationSpec;
        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, mPresentationSpec);
        this.mSource = source;

        if (!(java.util.Objects.equals(mSource, SOURCE_AUTOFILL))
                && !(java.util.Objects.equals(mSource, SOURCE_PLATFORM))) {
            throw new java.lang.IllegalArgumentException(
                    "source was " + mSource + " but must be one of: "
                            + "SOURCE_AUTOFILL(" + SOURCE_AUTOFILL + "), "
                            + "SOURCE_PLATFORM(" + SOURCE_PLATFORM + ")");
        }

        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, mSource);
        this.mAutofillHints = autofillHints;
        this.mType = type;

        if (!(java.util.Objects.equals(mType, TYPE_SUGGESTION))
                && !(java.util.Objects.equals(mType, TYPE_ACTION))) {
            throw new java.lang.IllegalArgumentException(
                    "type was " + mType + " but must be one of: "
                            + "TYPE_SUGGESTION(" + TYPE_SUGGESTION + "), "
                            + "TYPE_ACTION(" + TYPE_ACTION + ")");
        }

        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, mType);
        this.mPinned = pinned;

        // onConstructed(); // You can define this method to get a callback
    }

    /**
     * The presentation spec to which the inflated suggestion view abides.
     */
    @DataClass.Generated.Member
    public @NonNull InlinePresentationSpec getPresentationSpec() {
        return mPresentationSpec;
    }

    /**
     * The source from which the suggestion is provided.
     */
    @DataClass.Generated.Member
    public @NonNull @Source String getSource() {
        return mSource;
    }

    /**
     * Hints for the type of data being suggested.
     */
    @DataClass.Generated.Member
    public @Nullable String[] getAutofillHints() {
        return mAutofillHints;
    }

    /**
     * The type of the UI.
     */
    @DataClass.Generated.Member
    public @NonNull @Type String getType() {
        return mType;
    }

    /**
     * Whether the suggestion should be pinned or not.
     */
    @DataClass.Generated.Member
    public boolean isPinned() {
        return mPinned;
    }

    @Override
    @DataClass.Generated.Member
    public String toString() {
        // You can override field toString logic by defining methods like:
        // String fieldNameToString() { ... }

        return "InlineSuggestionInfo { " +
                "presentationSpec = " + mPresentationSpec + ", " +
                "source = " + mSource + ", " +
                "autofillHints = " + java.util.Arrays.toString(mAutofillHints) + ", " +
                "type = " + mType + ", " +
                "pinned = " + mPinned +
        " }";
    }

    @Override
    @DataClass.Generated.Member
    public boolean equals(@Nullable Object o) {
        // You can override field equality logic by defining either of the methods like:
        // boolean fieldNameEquals(InlineSuggestionInfo other) { ... }
        // boolean fieldNameEquals(FieldType otherValue) { ... }

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked")
        InlineSuggestionInfo that = (InlineSuggestionInfo) o;
        //noinspection PointlessBooleanExpression
        return true
                && java.util.Objects.equals(mPresentationSpec, that.mPresentationSpec)
                && java.util.Objects.equals(mSource, that.mSource)
                && java.util.Arrays.equals(mAutofillHints, that.mAutofillHints)
                && java.util.Objects.equals(mType, that.mType)
                && mPinned == that.mPinned;
    }

    @Override
    @DataClass.Generated.Member
    public int hashCode() {
        // You can override field hashCode logic by defining methods like:
        // int fieldNameHashCode() { ... }

        int _hash = 1;
        _hash = 31 * _hash + java.util.Objects.hashCode(mPresentationSpec);
        _hash = 31 * _hash + java.util.Objects.hashCode(mSource);
        _hash = 31 * _hash + java.util.Arrays.hashCode(mAutofillHints);
        _hash = 31 * _hash + java.util.Objects.hashCode(mType);
        _hash = 31 * _hash + Boolean.hashCode(mPinned);
        return _hash;
    }

    @Override
    @DataClass.Generated.Member
    public void writeToParcel(@NonNull android.os.Parcel dest, int flags) {
        // You can override field parcelling by defining methods like:
        // void parcelFieldName(Parcel dest, int flags) { ... }

        byte flg = 0;
        if (mPinned) flg |= 0x10;
        if (mAutofillHints != null) flg |= 0x4;
        dest.writeByte(flg);
        dest.writeTypedObject(mPresentationSpec, flags);
        dest.writeString(mSource);
        if (mAutofillHints != null) dest.writeStringArray(mAutofillHints);
        dest.writeString(mType);
    }

    @Override
    @DataClass.Generated.Member
    public int describeContents() { return 0; }

    /** @hide */
    @SuppressWarnings({"unchecked", "RedundantCast"})
    @DataClass.Generated.Member
    /* package-private */ InlineSuggestionInfo(@NonNull android.os.Parcel in) {
        // You can override field unparcelling by defining methods like:
        // static FieldType unparcelFieldName(Parcel in) { ... }

        byte flg = in.readByte();
        boolean pinned = (flg & 0x10) != 0;
        InlinePresentationSpec presentationSpec = (InlinePresentationSpec) in.readTypedObject(InlinePresentationSpec.CREATOR);
        String source = in.readString();
        String[] autofillHints = (flg & 0x4) == 0 ? null : in.createStringArray();
        String type = in.readString();

        this.mPresentationSpec = presentationSpec;
        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, mPresentationSpec);
        this.mSource = source;

        if (!(java.util.Objects.equals(mSource, SOURCE_AUTOFILL))
                && !(java.util.Objects.equals(mSource, SOURCE_PLATFORM))) {
            throw new java.lang.IllegalArgumentException(
                    "source was " + mSource + " but must be one of: "
                            + "SOURCE_AUTOFILL(" + SOURCE_AUTOFILL + "), "
                            + "SOURCE_PLATFORM(" + SOURCE_PLATFORM + ")");
        }

        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, mSource);
        this.mAutofillHints = autofillHints;
        this.mType = type;

        if (!(java.util.Objects.equals(mType, TYPE_SUGGESTION))
                && !(java.util.Objects.equals(mType, TYPE_ACTION))) {
            throw new java.lang.IllegalArgumentException(
                    "type was " + mType + " but must be one of: "
                            + "TYPE_SUGGESTION(" + TYPE_SUGGESTION + "), "
                            + "TYPE_ACTION(" + TYPE_ACTION + ")");
        }

        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, mType);
        this.mPinned = pinned;

        // onConstructed(); // You can define this method to get a callback
    }

    @DataClass.Generated.Member
    public static final @NonNull Parcelable.Creator<InlineSuggestionInfo> CREATOR
            = new Parcelable.Creator<InlineSuggestionInfo>() {
        @Override
        public InlineSuggestionInfo[] newArray(int size) {
            return new InlineSuggestionInfo[size];
        }

        @Override
        public InlineSuggestionInfo createFromParcel(@NonNull android.os.Parcel in) {
            return new InlineSuggestionInfo(in);
        }
    };

    @DataClass.Generated(
            time = 1582753084046L,
            codegenVersion = "1.0.14",
            sourceFile = "frameworks/base/core/java/android/view/inputmethod/InlineSuggestionInfo.java",
            inputSignatures = "public static final @android.view.inputmethod.InlineSuggestionInfo.Source java.lang.String SOURCE_AUTOFILL\npublic static final @android.view.inputmethod.InlineSuggestionInfo.Source java.lang.String SOURCE_PLATFORM\npublic static final @android.view.inputmethod.InlineSuggestionInfo.Type java.lang.String TYPE_SUGGESTION\npublic static final @android.annotation.SuppressLint({\"IntentName\"}) @android.view.inputmethod.InlineSuggestionInfo.Type java.lang.String TYPE_ACTION\nprivate final @android.annotation.NonNull android.view.inline.InlinePresentationSpec mPresentationSpec\nprivate final @android.annotation.NonNull @android.view.inputmethod.InlineSuggestionInfo.Source java.lang.String mSource\nprivate final @android.annotation.Nullable java.lang.String[] mAutofillHints\nprivate final @android.annotation.NonNull @android.view.inputmethod.InlineSuggestionInfo.Type java.lang.String mType\nprivate final  boolean mPinned\npublic static @android.annotation.TestApi @android.annotation.NonNull android.view.inputmethod.InlineSuggestionInfo newInlineSuggestionInfo(android.view.inline.InlinePresentationSpec,java.lang.String,java.lang.String[],java.lang.String,boolean)\nclass InlineSuggestionInfo extends java.lang.Object implements [android.os.Parcelable]\n@com.android.internal.util.DataClass(genEqualsHashCode=true, genToString=true, genHiddenConstDefs=true, genHiddenConstructor=true)")
    @Deprecated
    private void __metadata() {}


    //@formatter:on
    // End of generated code

}
