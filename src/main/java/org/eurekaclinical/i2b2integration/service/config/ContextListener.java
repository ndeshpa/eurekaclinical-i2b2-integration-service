package org.eurekaclinical.i2b2integration.service.config;

/*
 * #%L
 * i2b2 Export Service
 * %%
 * Copyright (C) 2013 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import org.eurekaclinical.i2b2integration.service.props.I2b2EurekaServicesProperties;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import javax.servlet.ServletContextEvent;
import org.eurekaclinical.common.config.ClientSessionListener;

/**
 * Configuration for Guice dependency injection.
 *
 * @author Michel Mansour
 * @since 1.0
 */
public final class ContextListener extends GuiceServletContextListener {

    private final I2b2EurekaServicesProperties properties = new I2b2EurekaServicesProperties();

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new JpaPersistModule("i2b2-jpa-unit"),
                new ServletModule(this.properties),
                new AppModule(this.properties));
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
        servletContextEvent.getServletContext().addListener(new ClientSessionListener());
    }
    
}
