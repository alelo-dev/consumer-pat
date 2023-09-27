package br.com.alelo.consumer.consumerpat.commandhandler;

import br.com.alelo.consumer.consumerpat.command.Command;

public interface CommandHandler<TCommand extends Command, R> {
    R handle(TCommand command);
}
    