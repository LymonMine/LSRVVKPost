package ru.lymonmine.lsrvvkpost;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class main extends JavaPlugin {
    public static main instance;

    @Override
    public void onEnable() {
        //команды
        getCommand("fakepost").setExecutor(new fakepostcmd(this));
        getCommand("latpost").setExecutor(new latpostcmd(this));
        //команды
        //конфиг
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        //конфиг
        //vkapi
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        GroupActor actor = new GroupActor(getConfig().getInt("vk-settings.groupid"), getConfig().getString("vk-settings.groupToken"));
        vk.groups().setLongPollSettings(actor, getConfig().getInt("vk-settings.groupid")).enabled(true).wallPostNew(true);
        longpollevent handler = new longpollevent(vk, actor);
        getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                try {
                    handler.run();
                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        });
        {


            instance = this;
        }
    }
}


