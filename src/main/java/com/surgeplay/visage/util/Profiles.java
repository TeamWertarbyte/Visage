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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.spacehq.mc.auth.data.GameProfile;
import org.spacehq.mc.auth.data.GameProfile.Property;
import org.spacehq.mc.auth.util.Base64;
import org.spacehq.mc.auth.util.UUIDSerializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Profiles {
    private static Gson gson = new GsonBuilder().registerTypeAdapter(UUID.class, new UUIDSerializer()).create();

    public static boolean isSlim(GameProfile profile) throws IOException {
        if (profile.getProperty("textures") != null) {
            String texJson = new String(Base64.decode(profile.getProperty("textures").getValue().getBytes(StandardCharsets.UTF_8)));
            JsonObject obj = gson.fromJson(texJson, JsonObject.class);
            JsonObject tex = obj.getAsJsonObject("textures");
            if (tex.has("SKIN")) {
                JsonObject skin = tex.getAsJsonObject("SKIN");
                if (skin.has("metadata")) {
                    if ("slim".equals(skin.getAsJsonObject("metadata").get("model").getAsString()))
                        return true;
                }
                return false;
            }
        }
        return UUIDs.isAlex(profile.getId());
    }

    public static GameProfile readGameProfile(DataInputStream data) throws IOException {
        boolean present = data.readBoolean();
        if (!present)
            return new GameProfile(new UUID(0, 0), "<unknown>");
        UUID uuid = new UUID(data.readLong(), data.readLong());
        String name = data.readUTF();
        GameProfile profile = new GameProfile(uuid, name);
        int len = data.readUnsignedShort();
        for (int i = 0; i < len; i++) {
            boolean signed = data.readBoolean();
            Property prop;
            if (signed) {
                prop = new Property(data.readUTF(), data.readUTF(), data.readUTF());
            } else {
                prop = new Property(data.readUTF(), data.readUTF());
            }
            data.readUTF(); // for backward-compatibility
            profile.getProperties().add(prop);
        }
        return profile;
    }

    public static void writeGameProfile(DataOutputStream data, GameProfile profile) throws IOException {
        if (profile == null) {
            data.writeBoolean(false);
            return;
        }
        data.writeBoolean(true);
        data.writeLong(profile.getId().getMostSignificantBits());
        data.writeLong(profile.getId().getLeastSignificantBits());
        data.writeUTF(profile.getName());
        data.writeShort(profile.getProperties().size());
        for (Property en : profile.getProperties()) {
            data.writeBoolean(en.hasSignature());
            if (en.hasSignature()) {
                data.writeUTF(en.getName());
                data.writeUTF(en.getValue());
                data.writeUTF(en.getSignature());
            } else {
                data.writeUTF(en.getName());
                data.writeUTF(en.getValue());
            }
            data.writeUTF(en.getName()); // for backward-compatibility
        }
    }
}
