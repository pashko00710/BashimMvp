package com.example.pashko00710.bashimmvp.injection.modules;import android.app.Application;import android.content.Context;import android.support.annotation.NonNull;import com.example.pashko00710.bashimmvp.injection.ApplicationContext;import dagger.Module;import dagger.Provides;@Modulepublic class ApplicationModule {    private final Application application;    public ApplicationModule(Application application) {        this.application = application;    }    @Provides    @NonNull    Application providesApplication() {return application;}    @Provides @NonNull    @ApplicationContext    Context providesContext() {return application;}}