package com.muver.chars.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class EncryptionService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Сервис создан", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Сервис получает задачу", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY; // Сервис завершит работу при отключении последнего клиента
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Подключение к сервису", Toast.LENGTH_SHORT).show();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Отключение от сервиса", Toast.LENGTH_SHORT).show();
        stopSelf();
        return super.onUnbind(intent);
    }
}
