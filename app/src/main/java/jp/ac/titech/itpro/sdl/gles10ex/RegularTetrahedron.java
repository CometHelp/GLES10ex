package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class RegularTetrahedron implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private float x, y, z;
    private static float rootTwo = (float)Math.sqrt(2);
    private static float rootThree = (float)Math.sqrt(3);

    public RegularTetrahedron(float s, float x, float y, float z) {
        float[] vertices = {
                // bottom
                0, 0, -2 * s,
                -rootThree * s, 0, s,
                rootThree * s, 0, s,
                // left
                0, 2 * rootTwo * s, 0,
                0, 0, -2 * s,
                -rootThree * s, 0, s,
                // right
                0, 2 * rootTwo * s, 0,
                0, 0, -2 * s,
                rootThree * s, 0, s,
                // front
                0, 2 * rootTwo * s, 0,
                -rootThree * s, 0, s,
                rootThree * s, 0, s,
        };
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);

        // left
        gl.glNormal3f(-rootThree, rootTwo / 2, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 3, 3);

        // right
        gl.glNormal3f(rootThree, rootTwo / 2, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 6, 3);

        // front
        gl.glNormal3f(0, 1, 2 * rootTwo);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 9, 3);
    }
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }
}

