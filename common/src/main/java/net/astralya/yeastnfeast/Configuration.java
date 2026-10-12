package net.astralya.yeastnfeast;

public final class Configuration {
    public static final IntValue TREE_TAP_DRIP_DELAY = new IntValue(7200);

    private Configuration() {
    }

    public static final class IntValue {
        private int value;

        private IntValue(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }

        public void set(int value) {
            this.value = value;
        }
    }
}
