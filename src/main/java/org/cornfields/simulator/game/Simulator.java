package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.Command;
import org.cornfields.simulator.command.RegisterCommand;
import org.cornfields.simulator.command.TravelCommand;
import org.cornfields.simulator.model.SmsResponse;

public class Simulator {

  private final FarmerDatabase farmerDatabase;
  private final CornfieldMap   cornfieldMap;

  public Simulator(FarmerDatabase farmerDatabase, CornfieldMap cornfieldMap) {
    this.farmerDatabase = farmerDatabase;
    this.cornfieldMap   = cornfieldMap;
  }

  public SmsResponse process(Command command) throws CommandNotAllowedException {
    switch (command.getType()) {
      case REGISTER:
        farmerDatabase.register((RegisterCommand) command);
        return new SmsResponse(command.getFarmerId(), "ok");

      case TRAVEL:
        cornfieldMap.travel((TravelCommand) command);
        return new SmsResponse(command.getFarmerId(), "ok");

      case CORN:
        return new SmsResponse(command.getFarmerId(), "ok");

      default:
        throw new IllegalArgumentException("I don't know about " + command.getType());
    }
  }

}