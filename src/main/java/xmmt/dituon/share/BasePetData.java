package xmmt.dituon.share;

import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class BasePetData {
    public static final float VERSION = 2.0F;

    public static boolean antialias = false;
    public static String command = "pet";
    public static int randomMax = 40;
    public static File dataRoot;
    public static ArrayList<String> disabledKey = new ArrayList<>();
    public static ArrayList<String> keyList = new ArrayList<>();
    public static HashMap<String, DataJSON> dataMap = new HashMap<>();

    public static void readData(File dir) {
        BasePetData.dataRoot = dir;
        String[] children = dir.list();

        if (children == null) {
            System.out.println("无法读取文件，请检查data目录");
            return;
        }

        for (String path : children) {

            File dataFile = new File(dir.getAbsolutePath() + File.separator + path + "/data.json");
            try {
                DataJSON data = ConfigJSONKt.getData(getFileStr(dataFile));
                if (!disabledKey.contains(path)) {
                    keyList.add(path);
                }
                dataMap.put(path, data);
            } catch (Exception ex) {
                System.out.println("无法读取 " + path + "/data.json: \n\n" + ex);
            }
        }

        randomMax = (int) (keyList.size() / (randomMax * 0.01));
        System.out.println("Petpet 加载完毕 (共 " + keyList.size() + " 素材，已排除 " + disabledKey.size() + " )");
    }

    public static void readConfig(File configFile) {

        try {
            if (configFile.exists()) {
                ConfigJSON config = ConfigJSONKt.decode(getFileStr(configFile));

                if (config.getVersion() != VERSION) {
                    createConfig(configFile);
                    return;
                }

                command = config.getCommand();
                antialias = config.getAntialias();
                randomMax = config.getProbability();

                for (JsonElement path : config.getDisabled()) {
                    disabledKey.add(path.toString().replace("\"", ""));
                }

                System.out.println("Petpet 初始化成功，使用 " + command + " 以生成GIF。");
            } else {
                createConfig(configFile);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) { // MissingFieldException
            createConfig(configFile);
        }
    }

    private static void createConfig(File configFile) {
        try {
            String defaultConfig = "{\n" +
                    "  \"version\": 2.0,\n" +
                    "  \"command\": \"pet\",\n" +
                    "  \"probability\": 30,\n" +
                    "  \"antialias\": false,\n" +
                    "  \"disabled\": [],\n" +
                    "  \"resPath\": \"./res/petpet/\"\n" +
                    "}";
            if (!configFile.createNewFile()) {
                System.out.print("正在写入新版本配置文件");
            }
            FileOutputStream defaultConfigOS = new FileOutputStream(configFile);
            defaultConfigOS.write(defaultConfig.getBytes(StandardCharsets.UTF_8));
            System.out.println("写入配置文件成功，路径: Mirai/plugins/petpet.json");
        } catch (IOException ex) {
            System.out.println("无法写入配置文件，请检查文件路径!");
        }
    }

    private static String getFileStr(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        return sb.toString();
    }

    public static InputStream generateImage(BufferedImage fromAvatarImage, BufferedImage toAvatarImage) {
        return generateImage(fromAvatarImage, toAvatarImage, BasePetData.keyList.get(new Random().nextInt(keyList.size())));
    }

    public static InputStream generateImage(BufferedImage fromAvatarImage, BufferedImage toAvatarImage, boolean random) {
        if (!random) {
            return generateImage(fromAvatarImage, toAvatarImage);
        }
        int r = new Random().nextInt(keyList.size());
        if (r >= keyList.size()) {
            return null;
        }
        return generateImage(fromAvatarImage, toAvatarImage, keyList.get(r));
    }

    public static InputStream generateImage(BufferedImage fromAvatarImage, BufferedImage toAvatarImage, String key) {
        if (!dataMap.containsKey(key)) {
            System.out.println("无效的key: " + key);
            return generateImage(fromAvatarImage, toAvatarImage);
        }
        DataJSON data = dataMap.get(key);
        key = dataRoot.getAbsolutePath() + File.separator + key + File.separator;

        try {
            if (data.getType() == Type.GIF) {
                if (data.getAvatar() == Avatar.SINGLE) {
                    int[][] pos = new int[data.getPos().getSize()][4];

                    int i = 0;
                    for (JsonElement je : data.getPos()) {
                        pos[i++] = JsonArrayToIntArray((JsonArray) je);
                    }

                    InputStream resultStream = BaseGifMaker.makeOneAvatarGIF(toAvatarImage, key, pos,
                            data.getAvatarOnTop(), data.getRotate(), data.getRound());
                    return resultStream;
                }
                if (data.getAvatar() == Avatar.DOUBLE) {
                    JsonArray fromJa = (JsonArray) data.getPos().get(0);
                    JsonArray toJa = (JsonArray) data.getPos().get(1);

                    int[][] fromPos = new int[fromJa.getSize()][4];
                    int[][] toPos = new int[toJa.getSize()][4];

                    int i = 0;
                    for (JsonElement fromJe : fromJa) {
                        fromPos[i++] = JsonArrayToIntArray((JsonArray) fromJe);
                    }
                    i = 0;
                    for (JsonElement toJe : toJa) {
                        toPos[i++] = JsonArrayToIntArray((JsonArray) toJe);
                    }

                    InputStream resultStream = BaseGifMaker.makeTwoAvatarGIF(fromAvatarImage, toAvatarImage, key, fromPos, toPos,
                            data.getAvatarOnTop(), data.getRotate(), data.getRound());
                    return resultStream;
                }
            }

            if (data.getType() == Type.IMG){
                if (data.getAvatar() == Avatar.SINGLE) {
                    int[] pos = JsonArrayToIntArray(data.getPos());

                    InputStream resultStream = BaseImageMaker.makeOneAvatarImage(toAvatarImage, key, pos,
                            data.getAvatarOnTop(), data.getRotate(), data.getRound());
                    return resultStream;
                }
                if (data.getAvatar() == Avatar.DOUBLE) {
                    int[] pos1 = JsonArrayToIntArray((JsonArray) data.getPos().get(0));
                    int[] pos2 = JsonArrayToIntArray((JsonArray) data.getPos().get(1));

                    InputStream resultStream = BaseImageMaker.makeTwoAvatarImage(fromAvatarImage, toAvatarImage, key, pos1, pos2,
                            data.getAvatarOnTop(), data.getRotate(), data.getRound());
                    return resultStream;
                }
            }
        } catch (Exception ex) {
            System.out.println("解析 " + key + "/data.json 出错");
            ex.printStackTrace();
        }
        return null;
    }

    private static int[] JsonArrayToIntArray(JsonArray ja) {
        return new int[]{
                Integer.parseInt(ja.get(0).toString()),
                Integer.parseInt(ja.get(1).toString()),
                Integer.parseInt(ja.get(2).toString()),
                Integer.parseInt(ja.get(3).toString())
        };
    }
}