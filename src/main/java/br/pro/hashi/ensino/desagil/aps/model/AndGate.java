package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nand;
    private final NandGate nandOut;


    public AndGate() {
        super(2);
        nand = new NandGate();
        nandOut = new NandGate();
    }

    @Override
    public boolean read() {
        return nand.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {

        if (inputPin != 0) {
            throw new IndexOutOfBoundsException(inputPin);
        }

        nand.connect(0, emitter);
        nand.connect(1, emitter);

        nandOut.connect(1,nand);
        nandOut.connect(0, nand);

    }
}