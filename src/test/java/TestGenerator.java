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
        /*
        5665922    - BlockPos{x=1724, y=19, z=-4769} 108 -295
        149491915  - BlockPos{x=-4885, y=27, z=-900} -305, -57
        296382583  - BlockPos{x=-2071, y=40, z=1582} -130, 95
         */
        StrongholdGenerator generator = new StrongholdGenerator(MCVersion.v1_16);
        generator.generate(296382583, -130, 95);
        //generator.generate(5665922, 108, -295);
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
