package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandC;
    private final NandGate nandD;


    public XorGate() {
        super(2);
        nandA = new NandGate();
        nandB = new NandGate();
        nandC = new NandGate();
        nandD = new NandGate();


    }

    @Override
    public boolean read() {
        return nandA.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin != 0) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        nandA.connect(0, emitter);
        nandA.connect(1, emitter);

        nandB.connect(0, emitter);
        nandB.connect(1, nandA);

        nandC.connect(0, emitter);
        nandC.connect(1, nandA);

        nandD.connect(0, nandB);
        nandD.connect(1, nandC);
    }
}
