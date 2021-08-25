package com.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 13:26
 */
public class DisplayStringRequest extends MethodRequest {

    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }
    @Override
    public void execute() {
        this.servant.displayString(text);

    }
}
