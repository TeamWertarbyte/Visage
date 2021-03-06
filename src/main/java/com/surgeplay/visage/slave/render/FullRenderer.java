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
package com.surgeplay.visage.slave.render;

import com.surgeplay.visage.slave.render.primitive.Cube;
import com.surgeplay.visage.slave.render.primitive.Plane;
import com.surgeplay.visage.slave.render.primitive.Stage;

public class FullRenderer extends Renderer {

	@Override
	protected void initPrimitives() {
		float tilt = -10;
		float angle = 20;
		
		Stage stage = new Stage();
		stage.x = 0;
		stage.y = -2.8f;
		stage.z = -10.35f;
		stage.rotX = tilt;
		stage.rotY = angle;
		addPrimitive(stage);
		
		Plane shadow = new Plane();
		shadow.y = 7f;
		shadow.scaleX = 1.85f;
		shadow.scaleZ = 0.85f;
		shadow.texture = TextureType.SHADOW;
		shadow.lit = false;
		shadow.alphaMode = AlphaMode.FULL;
		stage.members.add(shadow);
		
		Cube larm = new Cube();
		larm.x = 1.75f;
		larm.y = 2.375f;
		larm.z = -0.1f;
		larm.scaleY = 1.5f;
		larm.scaleZ = 0.5f;
		larm.scaleX = 0.5f;
		larm.rotZ = -10f;
		larm.texture = TextureType.LARM;
		larm.alphaMode = AlphaMode.NONE;
		stage.members.add(larm);
		Cube larm2 = new Cube();
		larm2.x = 1.7f;
		larm2.y = 2.35f;
		larm2.z = -0.1f;
		larm2.scaleY = 1.55f;
		larm2.scaleZ = 0.54f;
		larm2.scaleX = 0.55f;
		larm2.rotZ = -10f;
		larm2.texture = TextureType.LARM2;
		larm2.alphaMode = AlphaMode.MASK;
		stage.members.add(larm2);
		
		Cube lleg = new Cube();
		lleg.x = 0.5f;
		lleg.y = 5.425f;
		lleg.scaleY = 1.5f;
		lleg.scaleZ = 0.5f;
		lleg.scaleX = 0.5f;
		lleg.texture = TextureType.LLEG;
		lleg.alphaMode = AlphaMode.NONE;
		stage.members.add(lleg);
		
		Cube rleg = new Cube();
		rleg.x = -0.5f;
		rleg.y = 5.425f;
		rleg.scaleY = 1.5f;
		rleg.scaleZ = 0.5f;
		rleg.scaleX = 0.5f;
		rleg.texture = TextureType.RLEG;
		rleg.alphaMode = AlphaMode.NONE;
		stage.members.add(rleg);
		
		Cube body = new Cube();
		body.y = 2.475f;
		body.scaleY = 1.5f;
		body.scaleZ = 0.5f;
		body.texture = TextureType.BODY;
		body.alphaMode = AlphaMode.NONE;
		stage.members.add(body);
		Cube body2 = new Cube();
		body2.y = 2.5f;
		body2.scaleY = 1.55f;
		body2.scaleZ = 0.55f;
		body2.scaleX = 1.05f;
		body2.texture = TextureType.BODY2;
		body2.alphaMode = AlphaMode.MASK;
		stage.members.add(body2);
		
		Cube lleg2 = new Cube();
		lleg2.x = 0.475f;
		lleg2.y = 5.4f;
		lleg2.scaleY = 1.55f;
		lleg2.scaleZ = 0.55f;
		lleg2.scaleX = 0.55f;
		lleg2.texture = TextureType.LLEG2;
		lleg2.alphaMode = AlphaMode.MASK;
		stage.members.add(lleg2);
		
		Cube rleg2 = new Cube();
		rleg2.x = -0.525f;
		rleg2.y = 5.4f;
		rleg2.scaleY = 1.55f;
		rleg2.scaleZ = 0.55f;
		rleg2.scaleX = 0.55f;
		rleg2.texture = TextureType.RLEG2;
		rleg2.alphaMode = AlphaMode.MASK;
		stage.members.add(rleg2);
		
		Cube head = new Cube();
		head.y = -0.025f;
		head.z = -0.025f;
		head.texture = TextureType.HEAD;
		head.alphaMode = AlphaMode.NONE;
		stage.members.add(head);
		Cube helm = new Cube();
		helm.scaleX = helm.scaleY = helm.scaleZ = 1.05f;
		helm.texture = TextureType.HEAD2;
		helm.alphaMode = AlphaMode.MASK;
		stage.members.add(helm);
		
		Cube rarm = new Cube();
		rarm.x = -1.75f;
		rarm.y = 2.325f;
		rarm.z = 0.15f;
		rarm.scaleY = 1.5f;
		rarm.scaleZ = 0.5f;
		rarm.scaleX = 0.5f;
		rarm.rotZ = 10f;
		rarm.texture = TextureType.RARM;
		rarm.alphaMode = AlphaMode.NONE;
		stage.members.add(rarm);
		Cube rarm2 = new Cube();
		rarm2.x = -1.775f;
		rarm2.y = 2.3f;
		rarm2.z = 0.15f;
		rarm2.scaleY = 1.55f;
		rarm2.scaleZ = 0.55f;
		rarm2.scaleX = 0.55f;
		rarm2.rotZ = 10f;
		rarm2.texture = TextureType.RARM2;
		rarm2.alphaMode = AlphaMode.MASK;
		stage.members.add(rarm2);
	}

}
