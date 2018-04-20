# BouncingDVDLogo_libGDX

Now that I've actually gotten the program to build and compile to an iOS device, I'll write out the way to get there. Afterwards, I'll talk about the frustrations and issues I had along the way.

There is a setup app on badlogicgame's website to assist people in setting up a new project with libGDX. https://libgdx.badlogicgames.com/download.html This is a gradle-based setup to downloading everything you need for you.
I would follow this setup guide for setting up the IDE: https://github.com/libgdx/libgdx/wiki/Setting-up-your-Development-Environment-(Eclipse,-Intellij-IDEA,-NetBeans)
I chose IntelliJ IDEA CE with MobiDevelop's RoboVM plugin. I didn't need Android SDK, since this was only for iOS.
http://robovm.mobidevelop.com/

Once you have these things setup, you will want to setup your sample project. Open terminal and go to the downloads folder or location you saved the setup app for libGDX. You can follow the steps on this page, stating to use
`java -jar ./gdx-setup.jar`
https://github.com/libgdx/libgdx/wiki/Project-Setup-Gradle
I chose only iOS for subprojects on my final attempt, and I also chose IntelliJ's option in the Advanced section. This generates the base project for libGDX.

Open the project in IntelliJ IDEA CE with the .ipr file. Go to Run>Edit Configurations... Hit the + icon and create a new RoboVM iOS build. Name it ios. Make sure the module is ios. For an attached device, set the architect to the appropriate architect for that device (some iOS can't run particular architects). You may need to change the signing identity and provisioning profile from Auto to specific designation. I had to set the Signing Identity explicitly, since I had multiple identities on my machine. If these don't appear, you'll have to make sure that your signing identity and provisioning profile are setup through developer.apple.com and XCode. If you want to build to a simulator, you'll need to set the target to an existing simulator on your machine with its architecture.

Your program should build appropriately after these steps. It took me an extremely long time to compile to both the simulator and device (30+minutes), and I ran into memory issues on multiple occasions. After the first compile, it should only take a minute or less to compile and build. If you're having memory issues, you may want to go ahead and increase memory allocation to the IDE.

The coding for the bouncing dvd logo was straightforward. I exchanged the original image in the sample project with the bouncing dvd logo image. Then, I just had to look up some Gdx commands for changing color and moving the image around the screen.

https://gamedev.stackexchange.com/questions/25113/changing-the-color-of-a-sprite-at-runtime
More specifically, the following code helped change the dvd logo's color:

```Color c = new Color(batch.getColor());
batch.setColor(a,b,c,d);
batch.draw(yourSprite, x, y);
batch.setColor(c);
```

The background color of the screen was changed with `glClearColor` and `glClear`, which was similar to what I've done in OpenGL before. The `render()` function gets called during the game loop, so that's where the movement and color changing was placed into.

The actual coding was familiar and easy to work with. It felt natural, and I would like to do something more complex and interesting with the system. Unfortunately, the frustrations of trying to get the project to build to a device and the long compile times somewhat keep me at bay. I spent 18+ hours over several days banging my head against a wall trying to debug gradle functions for compiling the project to a device or simulator from the terminal. Several issues were related to the signing profile and provisioning profile. Yet, finding the solution just led to more problems and headaches. Eventually, I just turned back to the IDE after finding this post on how to properly set up the IDE for the project:

https://github.com/libgdx/libgdx/wiki/Gradle-and-Intellij-IDEA
