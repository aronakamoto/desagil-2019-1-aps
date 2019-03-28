package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandOut;


    public OrGate() {
        super(2);
        nandA = new NandGate();
        nandB = new NandGate();
        nandOut = new NandGate();
    }


    @Override
    public boolean read() {
        return nandA.read() && nandB.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin != 0) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        nandA.connect(0, emitter);
        nandA.connect(1, emitter);

        nandB.connect(0, emitter);
        nandB.connect(1, emitter);

        nandOut.connect(0, nandA);
        nandOut.connect(1, nandB);

    }
}