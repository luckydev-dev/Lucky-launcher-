/*
 * Zalith Launcher 2
 * Copyright (C) 2025-2026 MovTery <movtery228@qq.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/gpl-3.0.txt>.
 */

package com.movtery.petlib;

import android.graphics.Bitmap;

/**
 * PetLib 原生接口
 */
final class PetLibNative {
    private PetLibNative() {
    }

    static long nativeCreate(byte[] skin, int arm) {
        return 1L;
    }

    static void nativeDestroy(long ptr) {
    }

    static String nativeSetSkin(long ptr, byte[] skin, int arm) {
        return "";
    }

    static String nativeSetCape(long ptr, byte[] cape) {
        return "";
    }

    static void nativeSetLighting(long ptr, float azimuth, float elevation, float brightness, float softness) {
    }

    static void nativeSetSuperSample(long ptr, float value) {
    }

    static void nativeSetOuterLayer(long ptr, boolean enabled) {
    }

    static void nativeSetTau(long ptr, float gaze, float yaw, float anim, float recenter) {
    }

    static void nativeSetHeadRange(long ptr, float x, float y) {
    }

    static void nativeSetSquashDepth(long ptr, float depth) {
    }

    static void nativeSetGaze(long ptr, float x, float y, boolean hasTarget) {
    }

    static void nativeSetYaw(long ptr, float yaw) {
    }

    static float nativeGetYaw(long ptr) {
        return 0.0f;
    }

    static void nativePlayAnimation(long ptr, int id, float speed, boolean headFollow) {
    }

    static void nativeStopAnimation(long ptr) {
    }

    static void nativeSquashTap(long ptr, float now) {
    }

    static void nativePlaySwing(long ptr, float now) {
    }

    static void nativePlayInteract(long ptr) {
    }

    static void nativeRender(long ptr, int width, int height, float now, float dt, Bitmap bitmap) {
    }

    static boolean nativeHitTest(long ptr, float x, float y) {
        return false;
    }
}
