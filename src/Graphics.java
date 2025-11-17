import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Graphics {

    private long window;

    public void run() {
        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and the free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Set up an error callback.
        // The default will print the error in System.err
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most functions will not work before this
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize glfw");
        }

        // Configure GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // The window stays hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // The window is resizable

        // Create the window
        window = glfwCreateWindow(300, 300, "TESTING", NULL, NULL);

        if (window == NULL) {
            throw new RuntimeException("Failed to create the window");
        }

        // Set up the key callback. Called every time a key is pressed, repeated, or released
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true); // Detected in rendering loop
            }
        });

        // Gets the thread stack and pushes a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        // Make OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable v-sync (lol)
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

    }

    private void loop() {
        GL.createCapabilities(); // Apparently very important

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f,0.0f);

        // Run the rendering loop until close
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glfwSwapBuffers(window); // Swap the buffer colors

            // Checks for window events. Where key events are invoked
            glfwPollEvents();
        }
    }
}
