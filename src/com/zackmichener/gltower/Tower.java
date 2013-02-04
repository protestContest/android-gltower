package com.zackmichener.gltower;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;                            //1
import javax.microedition.khronos.opengles.GL11;

/**
 * A vertex shaded square.
 */
class Tower {
    public Tower() {
        float vertices[] =                                                   //2
        {
            -1.0f, -1.0f,
             1.0f, -1.0f,
            -1.0f,  1.0f,
             1.0f,  1.0f
        };

        byte maxColor=(byte)255;

        byte colors[] =                                                      //3
        {
            maxColor,maxColor,       0,maxColor,
            0,       maxColor,maxColor,maxColor,
            0,              0,       0,maxColor,
            maxColor,       0,maxColor,maxColor
        };
        
        byte indices[] =                                                     //4
        {
            0, 3, 1,
            0, 2, 3
        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);     //5
        vbb.order(ByteOrder.nativeOrder());
        mFVertexBuffer = vbb.asFloatBuffer();
        mFVertexBuffer.put(vertices);
        mFVertexBuffer.position(0);

        mColorBuffer = ByteBuffer.allocateDirect(colors.length);
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);

    }

    public void draw(GL10 gl)                                                //6
    {
        gl.glFrontFace(GL11.GL_CW);                                          //7
        gl.glVertexPointer(2, GL11.GL_FLOAT, 0, mFVertexBuffer);             //8
        gl.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 0, mColorBuffer);        //9
        gl.glDrawElements(GL11.GL_TRIANGLES, 6,                              //10
        GL11.GL_UNSIGNED_BYTE, mIndexBuffer);
        gl.glFrontFace(GL11.GL_CCW);                                         //11
    }

    private FloatBuffer mFVertexBuffer;
    private ByteBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
}
