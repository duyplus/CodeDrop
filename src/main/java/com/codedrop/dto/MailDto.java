package com.codedrop.dto;

import lombok.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto implements Comparable<MailDto> {

    String from;
    String to, subject, body;
    String[] cc, bcc;
    Map<String, byte[]> map = new HashMap<>();

    public MailDto(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    @SneakyThrows
    public void prepareToSend(MimeMessageHelper helper) {
        helper.setFrom("no-reply@gmail.com", "CodeDrop");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setReplyTo(from);
        if (cc != null && cc.length > 0) helper.setCc(cc);
        if (bcc != null && bcc.length > 0) helper.setBcc(bcc);
        map.forEach((name, array) -> addAttachment(name, array, helper));
    }

    @SneakyThrows
    private void addAttachment(String name, byte[] array, MimeMessageHelper helper) {
        helper.addAttachment(name, new ByteArrayResource(array));
    }

    @SneakyThrows
    public void setAttachments(MultipartFile[] attachments) {
        for (MultipartFile attach : attachments) {
            if (!attach.isEmpty() && attach.getSize() > 0) {
                map.put(attach.getOriginalFilename(), attach.getBytes());
            }
        }
    }

    @Override
    public int compareTo(MailDto o) {
        return 0;
    }
}
