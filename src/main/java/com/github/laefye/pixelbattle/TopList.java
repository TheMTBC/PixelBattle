package com.github.laefye.pixelbattle;

import com.github.laefye.pixelbattle.wrappers.JsonIO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.*;
import java.util.stream.Collectors;

public class TopList {
    private record TopListEntry(String player, int placed) {

    }
    private ArrayList<TopListEntry> topListEntries = new ArrayList<>();

    public void sort(Member member) {
        topListEntries = (ArrayList<TopListEntry>) topListEntries
                .stream()
                .filter(entry -> !entry.player.equals(member.getPlayer().getName()))
                .collect(Collectors.toList());
        topListEntries.add(new TopListEntry(member.getPlayer().getName(), member.getPlaced()));
        topListEntries = (ArrayList<TopListEntry>) topListEntries
                .stream()
                .sorted(Comparator.comparingInt(TopListEntry::placed))
                .limit(30)
                .collect(Collectors.toList());
        Collections.reverse(topListEntries);
    }

    public JsonObject getJsonObject() {
        var jsonObject = new JsonObject();
        for (var entry : topListEntries) {
            jsonObject.addProperty(entry.player(), entry.placed());
        }
        return jsonObject;
    }

    public void save() {
        JsonIO.save(getJsonObject(), SomeConstants.PLUGIN_FOLDER + "toplist.json");
    }

    public void load() {
        var jsonObject = JsonIO.load(SomeConstants.PLUGIN_FOLDER + "toplist.json");
        if (jsonObject != null) {
            for (var key : jsonObject.keySet()) {
                topListEntries.add(new TopListEntry(key, jsonObject.get(key).getAsInt()));
            }
        }
    }

    public String get(int i) {
        if (i >= topListEntries.size()) {
            return "";
        }
        var entry = topListEntries.get(i);
        return entry.player() + ": " + SomeConstants.stringifyInteger(entry.placed());
    }
}
