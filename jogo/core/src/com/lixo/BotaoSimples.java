package com.lixo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class BotaoSimples
{
    Rectangle area;
    TextureRegion imagem;
    public BotaoSimples(TextureRegion img,float x, float y, float w, float h)
    {
        area = new Rectangle(x, y, w, h);
        imagem = img;
    }
    
    public void setImagem(TextureRegion img)
    {
        imagem = img;
    }
    
    public void desenhar(Batch batch)
    {
        batch.draw(imagem, area.x, area.y);
    }
    
    public boolean checar_click(Vector3 area)
    {
        if (this.area.contains(area.x, area.y))
            return true;
        return false;
    }
}
