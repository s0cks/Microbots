package microbots.common.core;

import net.minecraft.nbt.NBTTagCompound;

import java.util.Arrays;

public final class Terminal{
  public final int width;
  public final int height;

  private int cursorX;
  private int cursorY;
  private int bgColor;
  private int fgColor;
  private boolean cursorBlink;
  private String empty;
  private String[] lines;

  public Terminal(){
    this(58, 21);
  }

  public Terminal(int width, int height) {
    this.width = width;
    this.height = height;

    this.cursorX = 0;
    this.cursorY = 0;
    this.cursorBlink = false;
    this.fgColor = 0xFFFFFF;
    this.bgColor = 0x111111;
    this.lines = new String[this.height];

    this.buildEmpty();
  }

  private void buildEmpty(){
    char[] spaces = new char[width];
    Arrays.fill(spaces, ' ');
    this.empty = new String(spaces);
    for (int i = 0; i < this.height; i++) {
      this.lines[i] = this.empty;
    }
  }

  public void setCursorPos(int x, int y){
    this.cursorX = x;
    this.cursorY = y;

    if(this.cursorX < 0){
      this.cursorX = 0;
    } else if(this.cursorX > this.width){
      this.cursorX = this.width;
    }

    if(this.cursorY < 0){
      this.cursorY = 0;
    } else if(this.cursorY > this.height){
      this.cursorY = this.height;
    }
  }

  public int getCursorX(){
    return this.cursorX;
  }

  public int getCursorY(){
    return this.cursorY;
  }

  public void write(String line){
    if(line == null || line.isEmpty()) return;
    try{
      if((this.cursorX >= 0 && this.cursorY < this.height)){
        int writeX = this.cursorX;
        int spaceLeft = this.width - this.cursorX;
        if(spaceLeft > this.width + line.length()){
          return;
        }

        if(spaceLeft > this.width){
          writeX = 0;
          line = line.substring(spaceLeft - this.width);
          spaceLeft = this.width;
        }

        line = line.replace("\t", "  ");
        if(spaceLeft > 0){
          String oldLine = this.lines[this.cursorY];
          StringBuilder newLine = new StringBuilder();
          newLine.append(oldLine.substring(0, writeX));

          if(line.length() < spaceLeft){
            newLine.append(line);
            newLine.append(oldLine.substring(writeX + line.length()));
          } else{
            newLine.append(line.substring(0, spaceLeft));
          }

          this.lines[this.cursorY] = newLine.toString();
        }
      }
    } catch(Exception e){
      e.printStackTrace(System.err);
    }
  }

  public void scroll(int dY){
    String[] newLines = new String[this.height];
    for(int y = 0; y < this.height; y++){
      int oldY = y + dY;
      if((oldY >= 0) && (oldY < this.height)){
        newLines[y] = this.lines[oldY];
      } else{
        newLines[y] = this.empty;
      }
    }
    this.lines = newLines;
  }

  public void clear(){
    for(int y = 0; y < this.height; y++){
      if(!this.lines[y].equals(this.empty)){
        this.lines[y] = this.empty;
      }
    }
  }

  public String getLine(int y){
    if((y >= 0) && (y < this.height)){
      return this.lines[y];
    }
    return "<error>";
  }

  public void readFromNBT(NBTTagCompound comp) {
    this.cursorX = comp.getInteger("cursorX");
    this.cursorY = comp.getInteger("cursorY");
    this.fgColor = comp.getInteger("fgColor");
    this.bgColor = comp.getInteger("bgColor");
    this.cursorBlink = comp.getBoolean("cursorBlink");
    for(int i = 0; i < this.height; i++){
      this.lines[i] = comp.getString("line_" + i);
    }
  }

  public void writeToNBT(NBTTagCompound comp) {
    comp.setInteger("cursorX", this.cursorX);
    comp.setInteger("cursorY", this.cursorY);
    comp.setInteger("fgColor", this.fgColor);
    comp.setInteger("bgColor", this.bgColor);
    comp.setBoolean("cursorBlink", this.cursorBlink);
    for(int i = 0; i < this.height; i++){
      comp.setString("line_" + i, this.lines[i]);
    }
  }
}