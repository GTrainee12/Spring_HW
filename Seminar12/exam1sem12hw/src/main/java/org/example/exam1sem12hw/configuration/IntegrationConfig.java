package org.example.exam1sem12hw.configuration;


import com.rometools.rome.feed.synd.SyndEntry;
import org.example.exam1sem12hw.model.Note;
import org.example.exam1sem12hw.repository.NoteRepository;
import org.example.exam1sem12hw.services.FileGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.feed.dsl.Feed;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;


@Configuration
public class IntegrationConfig {


    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private FileGateway fileGateway;


    @Bean
    public MessageChannel textInputChanel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChanel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "textInputChanel", outputChannel = "fileWriterChanel")
    public GenericTransformer<String, String> mainTransformer() {
        return text -> {
            return text;
        };
    }
    @Bean
    public IntegrationFlow feedFlow() throws MalformedURLException {
        return IntegrationFlow.from(Feed.inboundAdapter( new URL("https://lenta.ru/rss"), "news"),
                        e -> e.poller(p -> p.fixedDelay(5000)))
                .transform(extractLinkFromRSS())
                .get();
    }
    @Bean
    public AbstractPayloadTransformer<SyndEntry, String> extractLinkFromRSS() {
        return new AbstractPayloadTransformer<SyndEntry, String>() {
            @Override
            protected String transformPayload(SyndEntry payload) {
                Note note = new Note();
                note.setTitle(payload.getTitle());
                note.setContent(payload.getAuthor());
                note.setCreatedAt(LocalDateTime.now());
                noteRepository.save(note);
                fileGateway.writeToFile(  "News.txt", note.toString());
                return payload.getTitle() + "\n" + payload.getAuthor() + "\n" + payload.getLink();
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChanel")
    public FileWritingMessageHandler messageHandler() {
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File(
                        "D:/Spring_HW/Seminar12/exam1sem12hw"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}
