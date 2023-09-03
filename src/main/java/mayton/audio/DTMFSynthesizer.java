package mayton.audio;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * There are 12 DTMF chars
 *
 * lcm(12,256) = 768
 *
 *
 * 1     	2 	    3 	    A 	697 Гц
 * 4 	    5 	    6 	    B  	770 Гц
 * 7 	    8 	    9 	    C 	852 Гц
 * * 	    0 	    # 	    D 	941 Гц
 * 1209     1336    1477    1633
 */
public class DTMFSynthesizer {

    static final class DTMFKey {
        final int freq1;
        final int freq2;

        DTMFKey(int freq1, int freq2) {
            this.freq1 = freq1;
            this.freq2 = freq2;
        }
    }

    static DTMFKey generate(char c) {
        return DTMFS.get(c);
    }

    static final Map<Character, DTMFKey> DTMFS = Collections.unmodifiableMap(new LinkedHashMap<>() {{
        put('1', new DTMFKey(1209,697));
        put('2', new DTMFKey(1336,697));
        put('3', new DTMFKey(1477,697));
        put('A', new DTMFKey(1633,697));

        put('4', new DTMFKey(1209,770));
        put('5', new DTMFKey(1336,770));
        put('6', new DTMFKey(1477,770));
        put('B', new DTMFKey(1633,770));

        put('7', new DTMFKey(1209,852));
        put('8', new DTMFKey(1336,852));
        put('9', new DTMFKey(1477,852));
        put('C', new DTMFKey(1633,852));

        put('*', new DTMFKey(1209,941));
        put('0', new DTMFKey(1336,941));
        put('#', new DTMFKey(1477,941));
        put('D', new DTMFKey(1633,941));
    }});

}
