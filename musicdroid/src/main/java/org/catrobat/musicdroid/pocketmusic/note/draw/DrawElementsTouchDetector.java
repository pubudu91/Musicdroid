/*
 * Musicdroid: An on-device music generator for Android
 * Copyright (C) 2010-2015 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.musicdroid.pocketmusic.note.draw;

import java.util.List;

public class DrawElementsTouchDetector {

    public static final int INVALID_INDEX = -1;

    public int getIndexOfTouchedDrawElement(List<SymbolPosition> drawElements, float x, float y) {
        for (int i = 0; i < drawElements.size(); i++) {
            SymbolPosition element = drawElements.get(i);

            if ((element.getLeft() <= x) && (element.getRight() >= x) && (element.getTop() <= y) && (element.getBottom() >= y)) {
                return i;
            }
        }

        return INVALID_INDEX;
    }
}
