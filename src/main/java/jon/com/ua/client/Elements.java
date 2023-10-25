package jon.com.ua.client;


import com.codenjoy.dojo.services.printer.CharElement;

public enum Elements implements CharElement {
    BAD_APPLE('☻'),
    GOOD_APPLE('☺'),

    BREAK('☼'),

    HEAD_DOWN('▼'),
    HEAD_LEFT('◄'),
    HEAD_RIGHT('►'),
    HEAD_UP('▲'),

    TAIL_END_DOWN('╙'),
    TAIL_END_LEFT('╘'),
    TAIL_END_UP('╓'),
    TAIL_END_RIGHT('╕'),
    TAIL_HORIZONTAL('═'),
    TAIL_VERTICAL('║'),
    TAIL_LEFT_DOWN('╗'),
    TAIL_LEFT_UP('╝'),
    TAIL_RIGHT_DOWN('╔'),
    TAIL_RIGHT_UP('╚'),

    NONE(' ');

    final char ch;

    Elements(char ch) {
        this.ch = ch;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    @Override
    public char ch() {
        return ch;
    }

    public static Elements valueOf(char ch) {
        for (Elements el : Elements.values()) {
            if (el.ch == ch) {
                return el;
            }
        }
        throw new IllegalArgumentException("No such element for " + ch);
    }
}
