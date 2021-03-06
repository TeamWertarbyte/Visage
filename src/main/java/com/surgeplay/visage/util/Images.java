/*
 * Visage
 * Copyright (c) 2015-2016, Aesen Vismea <aesen@unascribed.com>
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.surgeplay.visage.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class Images {

	public static BufferedImage toBuffered(Image img) throws InterruptedException {
		if (img instanceof BufferedImage) return (BufferedImage) img;
		return toARGB(img);
	}

	public static BufferedImage toARGB(Image img) throws InterruptedException {
		MediaTracker tracker = new MediaTracker(new JButton("Dummy"));
		tracker.addImage(img, 0);
		tracker.waitForID(0);
		BufferedImage buf = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = buf.createGraphics();
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		return buf;
	}

}
