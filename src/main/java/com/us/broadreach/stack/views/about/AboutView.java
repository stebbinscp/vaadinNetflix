package com.us.broadreach.stack.views.about;


import com.us.broadreach.stack.models.Email;
import com.us.broadreach.stack.models.FavoriteItem;
import com.us.broadreach.stack.service.ResponseCallback;
import com.us.broadreach.stack.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Route(value = "about", layout = MainView.class)
@PageTitle("About Us")
@CssImport("./views/about.css")
public class AboutView extends Div {

    private TextField subject = new TextField("Subject");
    private TextArea body = new TextArea("Message");
    private Button send = new Button("Send");
    private String emailBaseUrl = "https://psfvl45h3m.execute-api.us-east-1.amazonaws.com/Prod/email";


    public AboutView() {
        addClassName("about");
       // body.addClassName("message");


        Span description = new Span("Please enjoy this website designed for storing your" +
                "netflix films and tv shows, allowing you to edit their descriptions in your" +
                "own database.");
        description.addClassName("text");

        add(description, createTitle(), createFormLayout(), createButtonLayout());


        send.addClickListener(e -> {
            if (subject.isEmpty() || body.isEmpty()) {
                openWarning("Fields cannot be blank");
            } else {
                Email email = new Email();
                email.setBody(body.getValue());
                email.setSubject(subject.getValue());
                email.setEmailFrom("some user");
                sendEmail(email);
                clearFields();

            }
        });

    }

    private void clearFields(){
        subject.setValue("");
        body.setValue("");
    }

    public void sendEmail(Email email)  {
        Notification success = new Notification(new Html(String.format(
                "<div class='email-sent-success'><h3>Successfully Sent Message</h3><h4>%s</h4><p>%s</p><div/>", email.getSubject(),
                email.getBody())));
        String formatted = emailBaseUrl;
        Mono<Email> mono = WebClient.create().post()
//
                .uri(formatted)
                .body(Mono.just(email),Email.class)
                .retrieve()
                .bodyToMono(Email.class);

        success.setDuration(3000);
        success.setPosition(Notification.Position.BOTTOM_CENTER);

        success.open();

    }

    public void openWarning(String errorMsg) {
        Notification notification = new Notification(
                errorMsg, 3000, Notification.Position.BOTTOM_CENTER);
        notification.open();
    }



    private Component createTitle() {
        return new H3("Contact Us");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep(StringUtils.EMPTY, 1));
        formLayout.add(subject, body);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        send.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(send);
        return buttonLayout;
    }

}
