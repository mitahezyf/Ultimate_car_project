import java.awt.event.KeyEvent;

public class Controlling implements Controls{

    private Input input;

    public Controlling(Input input){
        this.input = input;
    }
    @Override
    public boolean AccelerationKey() {
        return input.isPressed(KeyEvent.VK_W);
    }

    @Override
    public boolean BreakKey() {
        return input.isPressed(KeyEvent.VK_SPACE);
    }

    @Override
    public boolean EngineOnOffKey() {
        return input.isPressed(KeyEvent.VK_C);
    }

    @Override
    public boolean GearUpKey() {
        return input.isPressed(KeyEvent.VK_E);
    }

    @Override
    public boolean GearDownKey() {
        return input.isPressed(KeyEvent.VK_Q);
    }
}