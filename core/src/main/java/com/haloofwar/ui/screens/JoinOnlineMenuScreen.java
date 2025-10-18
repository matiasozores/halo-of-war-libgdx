package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.engine.events.CharacterTypedEvent;
import com.haloofwar.engine.utils.Text;
import com.haloofwar.ui.Menu;

public class JoinOnlineMenuScreen extends Menu {

    private final Text typedCodeText;
    private final Text promptText;

    public JoinOnlineMenuScreen(final GameContext CONTEXT, final Screen previousScreen) {
        super(CONTEXT, "Unirte a partida", new String[] { "Conectarse", "Volver" }, previousScreen, Background.MAIN_MENU);

        this.typedCodeText = new Text("", CONTEXT.getRender().getFont().getTitleFont());
        this.typedCodeText.setColor(Color.YELLOW);

        this.promptText = new Text("Ingresar co digo: ", CONTEXT.getRender().getFont().getTitleFont());
        this.promptText.setColor(Color.WHITE);

        this.listenerManager.add(context.getGlobalBus(), CharacterTypedEvent.class, this::onCharacterTyped);
    }
    
    private void onCharacterTyped(CharacterTypedEvent event) {
        char character = event.character;
         
        if(Character.isLetterOrDigit(character)) {
            String current = this.typedCodeText.getText();
            this.typedCodeText.setText(current + character);
        }

        if(character == '\b') { // borra con backspace
            String current = this.typedCodeText.getText();
            if(!current.isEmpty()) {
                this.typedCodeText.setText(current.substring(0, current.length() - 1));
            }
        }
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                String code = typedCodeText.getText().trim();
                if (!code.isEmpty()) {
                    this.connectToHost(code);
                } else {
                    System.out.println("Código vacío.");
                }
                break;
            case 1: 
                this.goBack();
                break;
        }
    }

    private void connectToHost(String code) {
        System.out.println("Intentando unirse con código: " + code);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        float x = 100;
        float y = 300;

        SpriteBatch batch = this.context.getRender().getBatch();
        batch.begin();
        drawTextWithOutline(promptText, batch, x, y, Color.WHITE, Color.BLACK);
        float offsetX = promptText.getWidth();
        drawTextWithOutline(typedCodeText, batch, x + offsetX, y, Color.YELLOW, Color.BLACK);
        batch.end();
    }
    
    private void drawTextWithOutline(Text text, SpriteBatch batch, float x, float y, Color textColor, Color outlineColor) {
        Color original = text.getColor();
        float offset = 2f;

        text.setColor(outlineColor);
        text.draw(batch, x - offset, y);
        text.draw(batch, x + offset, y);
        text.draw(batch, x, y - offset);
        text.draw(batch, x, y + offset);
        text.draw(batch, x - offset, y - offset);
        text.draw(batch, x + offset, y + offset);
        text.draw(batch, x - offset, y + offset);
        text.draw(batch, x + offset, y - offset);

        text.setColor(textColor);
        text.draw(batch, x, y);

        text.setColor(original);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
