/*
 * Musicdroid: An on-device music generator for Android
 * Copyright (C) 2010-2014 The Catrobat Team
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

package org.catrobat.musicdroid.pocketmusic.test.instrument;

import android.test.AndroidTestCase;

import org.catrobat.musicdroid.pocketmusic.instrument.TickProvider;
import org.catrobat.musicdroid.pocketmusic.note.NoteLength;
import org.catrobat.musicdroid.pocketmusic.note.Project;
import org.catrobat.musicdroid.pocketmusic.note.Track;
import org.catrobat.musicdroid.pocketmusic.test.note.TrackTestDataFactory;

public class TickProviderTest extends AndroidTestCase {

    private long[] currentTimeMillis;
    private TickProviderMock tickProvider;

    @Override
    protected void setUp() {
        currentTimeMillis = new long[] {100, 500};
        tickProvider = new TickProviderMock(Project.DEFAULT_BEATS_PER_MINUTE, currentTimeMillis);
    }

    public void testMockClassBehaviour() {
        for (long time : currentTimeMillis) {
            assertEquals(time,  tickProvider.currentTimeMillis());
        }
    }

    public void testSetTickBasedOnTrack() {
        Track track = TrackTestDataFactory.createSimpleTrack();

        tickProvider.setTickBasedOnTrack(track);

        long expectedTick = track.getLastTick();
        long actualTick = tickProvider.getTick();

        assertEquals(expectedTick, actualTick);
    }

    public void testCounting() {
        tickProvider.startCounting();
        tickProvider.stopCounting();

        long difference = currentTimeMillis[1] - currentTimeMillis[0];
        NoteLength noteLength = NoteLength.getNoteLengthFromMilliseconds(difference, Project.DEFAULT_BEATS_PER_MINUTE);
        long expectedTick = noteLength.toTicks(Project.DEFAULT_BEATS_PER_MINUTE);
        long actualTick = tickProvider.getTick();

        assertEquals(expectedTick, actualTick);
    }

    public void testIncreaseTick() {
        NoteLength noteLength = NoteLength.QUARTER;

        tickProvider.increaseTick(noteLength);

        assertEquals(noteLength.toTicks(Project.DEFAULT_BEATS_PER_MINUTE), tickProvider.getTick());
    }

    private class TickProviderMock extends TickProvider {

        private long[] currentTimeMillis;
        private int index;

        public TickProviderMock(int beatsPerMinute, long... currentTimeMillis) {
            super(beatsPerMinute);
            this.currentTimeMillis = currentTimeMillis;
            index = 0;
        }

        @Override
        protected long currentTimeMillis() {
            long result = currentTimeMillis[index];
            index++;

            return result;
        }
    }
}
