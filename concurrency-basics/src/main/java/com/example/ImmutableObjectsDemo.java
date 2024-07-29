package com.example;

import com.example.threadspawner.DirectThreadSpawner;

/**
 * There are some advantages, which immutable objects provide over mutable objects. One of the most important of them is
 * that we do not need to protect such objects from memory consistency errors. This demo's role is just to show exactly
 * this problem.
 */
public class ImmutableObjectsDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** Immutable objects demo *****");

        mutabilityMemoryInconsistency();
        immutabilityNoMemoryInconsistency();
    }

    private static void mutabilityMemoryInconsistency() {
        System.out.println("[mutability]: Gonna create blue color...");
        SynchronizedRGB blue = new SynchronizedRGB(0, 0, 255, "blue");

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("[mutability]: Inverting blue color...");
                blue.invert();
                int blueInversionRGB = blue.getRGB();
                Thread.sleep(5_000);
                String blueInversion = blue.getName();
                // Oops, another thread could change this in the meantime
                // We can get shot into foot due to mutable object memory inconsistencies very easily
                System.out.println("[mutability]: RGB of the " + blueInversion + " is: " + blueInversionRGB);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> blue.set(255, 255, 0, "yellow"));

        DirectThreadSpawner.spawnThread(t1);
        DirectThreadSpawner.spawnThread(t2);
    }

    private static void immutabilityNoMemoryInconsistency() {
        System.out.println("[immutability]: Gonna create blue color...");
        ImmutableRGB blue = new ImmutableRGB(0, 0, 255, "blue");

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("[immutability]: Inverting blue color...");
                ImmutableRGB blueInversion = blue.invert();
                int blueInversionRGB = blueInversion.getRGB();
                Thread.sleep(5_000);
                String blueInversionName = blueInversion.getName();
                // Everything's fine in this case, since it's immutable
                System.out.println("[immutability]: RGB of the " + blueInversionName + " is: " + blueInversionRGB);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Invalid: I cannot change the color, it's immutable
        // Thread t2 = new Thread(() -> blue.set(255, 255, 0, "yellow"));
        Thread t2 = new Thread(() -> {
            ImmutableRGB yellow = new ImmutableRGB(255, 255, 0, "yellow");
            System.out.println("[immutability]: Yellow RGB: " + yellow.getRGB());
        });

        DirectThreadSpawner.spawnThread(t1);
        DirectThreadSpawner.spawnThread(t2);
    }

    /**
     * Mutable version of {@link ImmutableRGB}.
     */
    private static class SynchronizedRGB {

        private volatile int red;
        private volatile int green;
        private volatile int blue;
        private volatile String name;

        public SynchronizedRGB(int red, int green, int blue, String name) {
            check(red, green, blue);
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }

        public synchronized void set(int red, int green, int blue, String name) {
            check(red, green, blue);
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }

        public synchronized int getRGB() {
            return ((red << 16) | (green << 8) | blue);
        }

        public synchronized String getName() {
            return name;
        }

        public synchronized void invert() {
            red = 255 - red;
            green = 255 - green;
            blue = 255 - blue;
            name = "Inverse of " + name;
        }

        private static void check(int red, int green, int blue) {
            if (red < 0 || red > 255
                    || green < 0 || green > 255
                    || blue < 0 || blue > 255) {
                throw new IllegalArgumentException(String.format("Invalid RGB value: (%d, %d, %d)", red, green, blue));
            }
        }
    }

    /**
     * Immutable version of {@link SynchronizedRGB}.
     */
    private static final class ImmutableRGB {

        private final int red;
        private final int green;
        private final int blue;
        private final String name;

        public ImmutableRGB(int red, int green, int blue, String name) {
            check(red, green, blue);
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }

        // Note: no need to synchronize methods, since it's immutable object
        public int getRGB() {
            return ((red << 16) | (green << 8) | blue);
        }

        // Note: no need to synchronize methods, since it's immutable object
        public String getName() {
            return name;
        }

        // Note: no need to synchronize methods, since it's immutable object
        public ImmutableRGB invert() {
            return new ImmutableRGB(255 - red, 255 - green, 255 - blue, "Inverse of " + name);
        }

        private static void check(int red, int green, int blue) {
            if (red < 0 || red > 255
                    || green < 0 || green > 255
                    || blue < 0 || blue > 255) {
                throw new IllegalArgumentException(String.format("Invalid RGB value: (%d, %d, %d)", red, green, blue));
            }
        }
    }
}
