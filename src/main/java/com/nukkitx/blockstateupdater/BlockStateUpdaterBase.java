package com.nukkitx.blockstateupdater;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nukkitx.blockstateupdater.util.tagupdater.CompoundTagUpdaterContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockStateUpdaterBase implements BlockStateUpdater {
    public static final BlockStateUpdater INSTANCE = new BlockStateUpdaterBase();

    public static final Map<String, Map<String, Object>[]> LEGACY_BLOCK_DATA_MAP = new HashMap<>();
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JsonNode node;
        try (InputStream stream = BlockStateUpdater.class.getClassLoader().getResourceAsStream("legacy_block_data_map.json")) {
            node = JSON_MAPPER.readTree(stream);
        } catch (IOException e) {
            throw new AssertionError("Error loading legacy block data map");
        }

        Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            String name = entry.getKey();
            JsonNode stateNodes = entry.getValue();

            int size = stateNodes.size();
            Map<String, Object>[] states = new Map[size];
            for (int i = 0; i < size; i++) {
                states[i] = convertStateToCompound(stateNodes.get(i));
            }

            LEGACY_BLOCK_DATA_MAP.put(name, states);
        }
    }

    private static Map<String, Object> convertStateToCompound(JsonNode node) {
        Map<String, Object> tag = new LinkedHashMap<>();
        Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            String name = entry.getKey();
            JsonNode value = entry.getValue();
            switch (value.getNodeType()) {
                case BOOLEAN:
                    tag.put(name, value.booleanValue());
                    break;
                case NUMBER:
                    tag.put(name, value.intValue());
                    break;
                case STRING:
                    tag.put(name, value.textValue());
                    break;
                default:
                    throw new UnsupportedOperationException("Invalid state type");
            }
        }
        return tag;
    }

    @Override
    public void registerUpdaters(CompoundTagUpdaterContext context) {
        context.addUpdater(0, 0, 0)
                .match("name", "minecraft:.+")
                .match("val", "[0-9]+")
                .addCompound("states")
                .tryEdit("states", helper -> {
                    Map<String, Object> tag = helper.getCompoundTag();
                    Map<String, Object> parent = helper.getParent();
                    String id = (String) parent.get("name");
                    short val = (short) parent.get("val");
                    Map<String, Object>[] statesArray = LEGACY_BLOCK_DATA_MAP.get(id);
                    if (statesArray != null) {
                        tag.putAll(statesArray[val]);
                    }
                })
                .remove("val");

        // This is not a vanilla state updater. In vanilla 1.16, the invalid block state is updated when the chunk is
        // loaded in so it can generate the connection data however the state set below should never occur naturally.
        // Checking for this block state instead means we don't have to break our loading system in order to support it.
        context.addUpdater(0, 0, 0)
                .match("name", "minecraft:.+_wall$")
                .tryEdit("states", helper -> {
                    Map<String, Object> states = helper.getCompoundTag();
                    states.put("wall_post_bit", (byte) 0);
                    states.put("wall_connection_type_north", "none");
                    states.put("wall_connection_type_east", "none");
                    states.put("wall_connection_type_south", "none");
                    states.put("wall_connection_type_west", "none");
                });
    }
}
