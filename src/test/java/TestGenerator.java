import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.featureutils.structure.generator.piece.StructurePiece;
import kaptainwutax.featureutils.structure.generator.piece.stronghold.PortalRoom;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.util.BlockBox;
import kaptainwutax.seedutils.util.math.Vec3i;

public class TestGenerator {

    public static BlockBox getEndPortalBB(PortalRoom portalRoom) {
        Vec3i mins = portalRoom.applyVecTransform(new Vec3i(3,3,8));
        Vec3i maxes = portalRoom.applyVecTransform(new Vec3i(7,3,12));
        return new BlockBox(mins,maxes);
    }

    public static boolean intesectsChunk(int chunkX, int chunkZ, BlockBox box) {
        chunkX <<= 4;
        chunkZ <<= 4;
        return box.intersectsXZ(chunkX, chunkZ, chunkX+16, chunkZ+16);
    }

    public static void main(String[] args) {
        StrongholdGenerator generator = new StrongholdGenerator(MCVersion.v1_15);
        generator.generate(-7316166329940488453L,130,-5);
        for (StructurePiece piece : generator.pieceList) {
            BlockBox box = piece.getBoundingBox();
            if(piece instanceof PortalRoom) {
                System.out.println((piece.getClass().toString()).split("\\.")[6] + " " + box.minX + " " + box.minZ+" to "+box.maxX+" "+box.maxZ+" facing " +
                        piece.getFacing());
                BlockBox portal = getEndPortalBB((PortalRoom) piece);
                System.out.println(portal.minX+" "+portal.minZ+" "+portal.maxX+" "+portal.maxZ);
            }

        }
    }
}
