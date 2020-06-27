package com.nukkitx.blockstateupdater.util.tagupdater;

import com.nukkitx.nbt.NbtMap;
import com.nukkitx.nbt.NbtMapBuilder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("unchecked")
public class CompoundTagEditHelper {
    private final Deque<Object> parentTag = new ArrayDeque<>();
    private final Deque<String> tagName = new ArrayDeque<>();
    private Map<String, Object> rootTag;
    private Object tag;

    public CompoundTagEditHelper(Map<String, Object> tag) {
        this.rootTag = tag;
        this.tag = tag;
    }

    public Map<String, Object> getRootTag() {
        return rootTag;
    }

    public Map<String, Object> getCompoundTag() {
        return (Map<String, Object>) tag;
    }

    public Object getTag() {
        return tag;
    }

    public Map<String, Object> getParent() {
        if (!this.parentTag.isEmpty()) {
            Object tag = this.parentTag.peekLast();
            if (tag instanceof Map) {
                return (Map<String, Object>) tag;
            }
        }
        return null;
    }

    public boolean canPopChild() {
        return !this.parentTag.isEmpty();
    }

    public void popChild() {
        if (!this.parentTag.isEmpty()) {
            this.tag = this.parentTag.pollLast();
            this.tagName.pollLast();
        }
    }

    public void pushChild(String name) {
        requireNonNull(name, "name");
        if (!(tag instanceof Map)) throw new IllegalStateException("Tag is not of Compound type");
        this.parentTag.addLast(this.tag);
        this.tagName.addLast(name);
        this.tag = ((Map<String, Object>) this.tag).get(name);
    }

    public void replaceWith(String name, Object newTag) {
        this.tag = newTag;
        if (this.parentTag.isEmpty()) {
            this.rootTag = ((NbtMap) tag).toBuilder();
            return;
        }
        Object tag = this.parentTag.getLast();
        if (tag instanceof NbtMap) {
            this.parentTag.removeLast();
            NbtMap parentTag = (NbtMap) tag;
            NbtMapBuilder newParent = parentTag.toBuilder();
            newParent.remove(this.tagName.pollLast());
            newParent.put(name, newTag);
            this.parentTag.offerLast(newParent);
            this.tagName.offerLast(name);
        }
    }
}
