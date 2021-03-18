# BlockStateUpdater

Extracted block state mappings from the Minecraft: Bedrock Edition used to update versioned NBT tags found in LevelDB
worlds to the latest version.

### Usage

Updating legacy meta values to block states

```java
NbtMap updatedTag=BlockStateUpdaters.updateBlockState(NbtMap.builder()
        .putString("name","minecraft:stone")
        .putShort("val",(short)1)
        .build(),0);
```

### Maven

```xml

<repositories>
    <repository>
        <id>nukkitx-repo-release</id>
        <url>https://repo.nukkitx.com/maven-releases/</url>
    </repository>
</repositories>

<dependencies>
<dependency>
    <groupId>com.nukkitx</groupId>
    <artifactId>block-state-updater</artifactId>
    <version>1.16.210.5</version>
</dependency>
</dependencies>
```