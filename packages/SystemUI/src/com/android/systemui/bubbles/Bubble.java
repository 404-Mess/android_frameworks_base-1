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
package com.android.systemui.bubbles;


import static android.view.Display.INVALID_DISPLAY;

import static com.android.internal.annotations.VisibleForTesting.Visibility.PRIVATE;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.view.LayoutInflater;

import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.R;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

import java.util.Objects;

/**
 * Encapsulates the data and UI elements of a bubble.
 */
class Bubble {
    private final String mKey;
    private final String mGroupId;
    private String mAppName;
    private NotificationEntry mEntry;

    private boolean mInflated;
    private BubbleView mIconView;
    private BubbleExpandedView mExpandedView;
    private long mLastUpdated;
    private long mLastAccessed;
    private boolean mIsRemoved;

    public static String groupId(NotificationEntry entry) {
        UserHandle user = entry.notification.getUser();
        return user.getIdentifier() + "|" + entry.notification.getPackageName();
    }

    /** Used in tests when no UI is required. */
    @VisibleForTesting(visibility = PRIVATE)
    Bubble(Context context, NotificationEntry e) {
        mEntry = e;
        mKey = e.key;
        mLastUpdated = e.notification.getPostTime();
        mGroupId = groupId(e);

        PackageManager pm = context.getPackageManager();
        ApplicationInfo info;
        try {
            info = pm.getApplicationInfo(
                mEntry.notification.getPackageName(),
                PackageManager.MATCH_UNINSTALLED_PACKAGES
                    | PackageManager.MATCH_DISABLED_COMPONENTS
                    | PackageManager.MATCH_DIRECT_BOOT_UNAWARE
                    | PackageManager.MATCH_DIRECT_BOOT_AWARE);
            if (info != null) {
                mAppName = String.valueOf(pm.getApplicationLabel(info));
            }
        } catch (PackageManager.NameNotFoundException unused) {
            mAppName = mEntry.notification.getPackageName();
        }
    }

    public String getKey() {
        return mKey;
    }

    public NotificationEntry getEntry() {
        return mEntry;
    }

    public String getGroupId() {
        return mGroupId;
    }

    public String getPackageName() {
        return mEntry.notification.getPackageName();
    }

    public String getAppName() {
        return mAppName;
    }

    boolean isInflated() {
        return mInflated;
    }

    public void updateDotVisibility() {
        if (mIconView != null) {
            mIconView.updateDotVisibility(true /* animate */);
        }
    }

    public BubbleView getIconView() {
        return mIconView;
    }

    public BubbleExpandedView getExpandedView() {
        return mExpandedView;
    }

    void inflate(LayoutInflater inflater, BubbleStackView stackView) {
        if (mInflated) {
            return;
        }
        mIconView = (BubbleView) inflater.inflate(
                R.layout.bubble_view, stackView, false /* attachToRoot */);
        mIconView.setNotif(mEntry);

        mExpandedView = (BubbleExpandedView) inflater.inflate(
                R.layout.bubble_expanded_view, stackView, false /* attachToRoot */);
        mExpandedView.setEntry(mEntry, stackView, mAppName);

        mInflated = true;
    }

    /**
     * Set visibility of bubble in the expanded state.
     *
     * @param visibility {@code true} if the expanded bubble should be visible on the screen.
     *
     * Note that this contents visibility doesn't affect visibility at {@link android.view.View},
     * and setting {@code false} actually means rendering the expanded view in transparent.
     */
    void setContentVisibility(boolean visibility) {
        if (mExpandedView != null) {
            mExpandedView.setContentVisibility(visibility);
        }
    }

    void setRemoved() {
        mIsRemoved = true;
        // TODO: move this somewhere where it can be guaranteed not to run until safe from flicker
        if (mExpandedView != null) {
            mExpandedView.cleanUpExpandedState();
        }
    }

    void setRemoved(boolean removed) {
        mIsRemoved = removed;
    }

    public boolean isRemoved() {
        return mIsRemoved;
    }

    void setEntry(NotificationEntry entry) {
        this.mEntry = entry;
        mLastUpdated = entry.notification.getPostTime();
        if (mInflated) {
            mIconView.update(entry);
            mExpandedView.update(entry);
        }
    }

    /**
     * @return the newer of {@link #getLastUpdateTime()} and {@link #getLastAccessTime()}
     */
    public long getLastActivity() {
        return Math.max(mLastUpdated, mLastAccessed);
    }

    /**
     * @return the timestamp in milliseconds of the most recent notification entry for this bubble
     */
    public long getLastUpdateTime() {
        return mLastUpdated;
    }

    /**
     * @return the timestamp in milliseconds when this bubble was last displayed in expanded state
     */
    public long getLastAccessTime() {
        return mLastAccessed;
    }

    /**
     * @return the display id of the virtual display on which bubble contents is drawn.
     */
    int getDisplayId() {
        return mExpandedView != null ? mExpandedView.getVirtualDisplayId() : INVALID_DISPLAY;
    }

    /**
     * Should be invoked whenever a Bubble is accessed (selected while expanded).
     */
    void markAsAccessedAt(long lastAccessedMillis) {
        mLastAccessed = lastAccessedMillis;
        mEntry.setShowInShadeWhenBubble(false);
    }

    /**
     * @return whether bubble is from a notification associated with a foreground service.
     */
    public boolean isOngoing() {
        return mEntry.isForegroundService();
    }

    @Override
    public String toString() {
        return "Bubble{" + mKey + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bubble)) return false;
        Bubble bubble = (Bubble) o;
        return Objects.equals(mKey, bubble.mKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mKey);
    }
}
