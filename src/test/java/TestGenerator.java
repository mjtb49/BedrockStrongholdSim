import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.featureutils.structure.generator.piece.StructurePiece;
import kaptainwutax.featureutils.structure.generator.piece.stronghold.PortalRoom;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.util.BlockBox;

public class TestGenerator {

    public static boolean intesectsChunk(int chunkX, int chunkZ, BlockBox box) {
        chunkX <<= 4;
        chunkZ <<= 4;
        return box.intersectsXZ(chunkX, chunkZ, chunkX+16, chunkZ+16);
    }

    public static void main(String[] args) {
        StrongholdGenerator generator = new StrongholdGenerator(MCVersion.v1_15);
        generator.generate(987654321,-51,14);
        for (StructurePiece piece : generator.pieceList) {
            BlockBox box = piece.getBoundingBox();
            if(piece instanceof PortalRoom) {
                System.out.println((piece.getClass().toString()).split("\\.")[6] + " " + box.minX + " " + box.minZ+" to "+box.maxX+" "+box.maxZ+" facing " +
                        piece.getFacing());
                BlockBox portal = ((PortalRoom) piece).getEndFrameBB();
                System.out.println(portal.minX +" "+portal.minZ+" "+portal.maxX+" "+portal.maxZ);
            }
        }
    }
}
