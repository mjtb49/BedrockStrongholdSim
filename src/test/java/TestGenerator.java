import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.featureutils.structure.generator.piece.StructurePiece;
import kaptainwutax.featureutils.structure.generator.piece.stronghold.PortalRoom;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.util.BlockBox;

import java.util.HashSet;

public class TestGenerator {

    public static boolean intesectsChunk(int chunkX, int chunkZ, BlockBox box) {
        chunkX <<= 4;
        chunkZ <<= 4;
        return box.intersectsXZ(chunkX, chunkZ, chunkX+16, chunkZ+16);
    }

    public static void main(String[] args) {
        //     HashSet<Integer> r = new HashSet();
        //     int[] goodSeeds = {
        //             0x29315EAB,
        //             0xF7870102,
        //             0xB20AEBDD,
        //             0x7090D2B4,
        //             0x3F66850F,
        //             0xFDEC6FE6,
        //             0xB8725641,
        //             0x46F83918,
        //             0x054FE3F3,
        //             0x0C0BA01B,
        //             0xCA918AF2,
        //             0x89677D4D,
        //             0x57ED2424,
        //             0x12730EFF,
        //             0xD0C6F156,
        //             0x9F4CD831,
        //             0x5DD28288
        //     };
        //     for (int i: goodSeeds) {
        //         r.add(i);
        //     }
        //     //594, -750
        //     int xCenter = 5;//594 >> 4;
        //     int zCenter = -44;//-750 >> 4;
        //     for (int i = xCenter - 32; i < xCenter + 32; i++)
        //         for (int j = zCenter - 32; j < zCenter + 32; j++) {
        //             JRand rand = new JRand(-643373631);
        //             int a = rand.nextInt()|1;
        //             int b = rand.nextInt()|1;
        //             int seed = (i*(a)+(b)*j)^-643373631;
        //             if (r.contains(seed)) {
        //                 System.out.println("Found Matching Chunkseed At "+i+" "+j);
        //             }
        //         }
        // }
        StrongholdGenerator generator = new StrongholdGenerator(MCVersion.v1_16);
        generator.generate(987654321, -51, 13);
        for (StructurePiece piece : generator.pieceList) {
            BlockBox box = piece.getBoundingBox();
            if (piece instanceof PortalRoom) {
                System.out.println((piece.getClass().toString()).split("\\.")[6] + " " + box.minX + " " + box.minZ + " to " + box.maxX + " " + box.maxZ + " facing " +
                        piece.getFacing());
                BlockBox portal = ((PortalRoom) piece).getEndFrameBB();
                System.out.println(portal.minX + " " + portal.minZ + " " + portal.maxX + " " + portal.maxZ);
            }
        }
    }
}
