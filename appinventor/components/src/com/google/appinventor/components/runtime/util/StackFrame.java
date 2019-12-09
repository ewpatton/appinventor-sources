package com.google.appinventor.components.runtime.util;

import gnu.mapping.Symbol;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StackFrame implements Cloneable {

  private static ThreadLocal<List<StackFrame>> frames = new ThreadLocal<List<StackFrame>>() {
    @Override
    protected List<StackFrame> initialValue() {
      return new LinkedList<>();
    }
  };

  private Deque<String> blockIds;
  private Map<Symbol, Object> values;

  public StackFrame(String blockId) {
    this.blockIds = new LinkedList<>();
    this.values = new HashMap<>();
    this.blockIds.add(blockId);
  }

  public String getBlockId() {
    return blockIds.getFirst();
  }

  public List<String> getBlockIds() {
    return (LinkedList<String>) blockIds;
  }

  public void push(String newBlockId) {
    blockIds.push(newBlockId);
  }

  public String pop() {
    return blockIds.pop();
  }

  public Map<Symbol, Object> getVariables() {
    return values;
  }

  public void set(Symbol symbol, Object value) {
    values.put(symbol, value);
  }

  public JSONObject toJson() throws JSONException {
    JSONObject result = new JSONObject();
    result.put("blockIds", JsonUtil.toJsonValue(blockIds));
    result.put("vars", JsonUtil.toJsonValue(values));
    return result;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public static List<StackFrame> get() {
    return frames.get();
  }
}
