package com.existencesmp.museum

import io.papermc.paper.event.player.AsyncChatDecorateEvent
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs
import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.bukkit.title
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.gui.openGUI
import net.axay.kspigot.items.addLore
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.axay.kspigot.main.KSpigot
import net.axay.kspigot.runnables.taskRunLater
import net.axay.kspigot.utils.fireworkItemStack
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.format.TextColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import java.time.Duration

class Museum : KSpigot() {
    companion object {
        lateinit var INSTANCE: Museum
    }

    override fun load() {
        INSTANCE = this
    }

    override fun startup() {
        val navigator = itemStack(Material.RECOVERY_COMPASS) {
            meta {
                name = literalText("Museum Navigator") {
                    color = KColors.AQUA
                    italic = false
                }
                addLore {
                    +literalText("→ ʀɪɢʜᴛ-ᴄʟɪᴄᴋ ᴛᴏ ᴏᴘᴇɴ") {
                        color = KColors.LIGHTGRAY
                        italic = false
                    }
                }
            }
        }

        val navigatorGui = kSpigotGUI(GUIType.FOUR_BY_NINE) {
            title = literalText("Museum Navigator")

            page(1) {
                placeholder(Slots.Border, itemStack(Material.GRAY_STAINED_GLASS_PANE) {
                    meta {
                        name = literalText("")
                    }
                })

                val seasonOneIcon = itemStack(Material.YELLOW_TERRACOTTA) {
                    meta {
                        name = literalText("Season 1") {
                            color = KColors.GOLD
                            italic = false
                        }
                        addLore {
                            +literalText("March 20th 2016 to August 4th 2017") {
                                color = TextColor.color(255, 224, 161)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.12.2 • 2.61GB • 925,603 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val seasonTwoIcon = itemStack(Material.GREEN_TERRACOTTA) {
                    meta {
                        name = literalText("Season 2") {
                            color = KColors.DARKGREEN
                            italic = false
                        }
                        addLore {
                            +literalText("August 4th 2017 to March 16th 2019") {
                                color = TextColor.color(167, 250, 167)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.13.2 • 2.59GB • 732,273 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val seasonThreeIcon = itemStack(Material.RED_TERRACOTTA) {
                    meta {
                        name = literalText("Season 3") {
                            color = KColors.DARKRED
                            italic = false
                        }
                        addLore {
                            +literalText("May 1st 2019 to March 18th 2020") {
                                color = TextColor.color(255, 184, 184)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.15.2 • 2.15GB • 839,460 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val hardcoreIcon = itemStack(Material.TOTEM_OF_UNDYING) {
                    meta {
                        name = literalText("Project Hardcore") {
                            color = KColors.DARKRED
                            italic = false
                        }
                        addLore {
                            +literalText("March 20th 2019 to April 24th 2019") {
                                color = TextColor.color(255, 184, 184)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.13.2 • 148MB • 34,937 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val keystoneIcon = itemStack(Material.TRIPWIRE_HOOK) {
                    meta {
                        name = literalText("Project Keystone") {
                            color = KColors.RED
                            italic = false
                        }
                        addLore {
                            +literalText("May 1st 2019 to June 27th 2020") {
                                color = TextColor.color(255, 184, 184)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.15.2 • 3.4GB • 1,080,016 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val amplifiedIcon = itemStack(Material.GOAT_HORN) {
                    meta {
                        name = literalText("Project Amplified") {
                            color = KColors.DARKGRAY
                            italic = false
                        }
                        addLore {
                            +literalText("December 1st 2021 to March 16th 2022") {
                                color = TextColor.color(196, 196, 196)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.17.1 • 951.6MB • 180,015 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                        addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE)
                    }
                }

                val createIcon = itemStack(Material.STONECUTTER) {
                    meta {
                        name = literalText("Project Create") {
                            color = TextColor.color(81, 87, 121)
                            italic = false
                        }
                        addLore {
                            +literalText("TBA") {
                                color = TextColor.color(179, 187, 230)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("x ɴᴏᴛ ᴀʀᴄʜɪᴠᴇᴅ x") {
                                color = KColors.GRAY
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("No Data Available") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val communityOneIcon = itemStack(Material.YELLOW_CONCRETE) {
                    meta {
                        name = literalText("Community Server 1") {
                            color = KColors.GOLD
                            italic = false
                        }
                        addLore {
                            +literalText("June 28th 2020 to December 1st 2021") {
                                color = TextColor.color(255, 224, 161)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.17.1 • 2.63GB • 638,288 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val communityTwoIcon = itemStack(Material.GREEN_CONCRETE) {
                    meta {
                        name = literalText("Community Server 2") {
                            color = KColors.DARKGREEN
                            italic = false
                        }
                        addLore {
                            +literalText("March 19th 2022 to Present") {
                                color = TextColor.color(167, 250, 167)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("x ɴᴏᴛ ᴀʀᴄʜɪᴠᴇᴅ x") {
                                color = KColors.GRAY
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("No Data Available") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                button(Slots.RowThreeSlotTwo, seasonOneIcon) {
                    server.dispatchCommand(server.consoleSender, "mvtp ${it.player.name} s1")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowThreeSlotThree, seasonTwoIcon) {
                    server.dispatchCommand(server.consoleSender, "mvtp ${it.player.name} s2")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowThreeSlotFour, seasonThreeIcon) {
                    server.dispatchCommand(server.consoleSender, "mvtp ${it.player.name} s3")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }

                button(Slots.RowTwoSlotThree, hardcoreIcon) {
                    server.dispatchCommand(server.consoleSender, "mvtp ${it.player.name} hardcore")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowTwoSlotFour, keystoneIcon) {
                    server.dispatchCommand(server.consoleSender, "mvtp ${it.player.name} keystone")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowTwoSlotFive, amplifiedIcon) {
                    server.dispatchCommand(server.consoleSender, "mvtp ${it.player.name} amplified")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowTwoSlotSix, createIcon) {
                    it.player.playSound(Sound.sound(Key.key("item.shield.break"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
                }

                button(Slots.RowThreeSlotSix, communityOneIcon) {
                    server.dispatchCommand(server.consoleSender, "mvtp ${it.player.name} cs1")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowThreeSlotSeven, communityTwoIcon) {
                    it.player.playSound(Sound.sound(Key.key("item.shield.break"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
                }

                button(Slots.RowFourSlotFive, itemStack(Material.OCHRE_FROGLIGHT) {
                    meta {
                        name = literalText("Museum Hub") {
                            color = KColors.GOLD
                            italic = false
                        }
                        addLore {
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                        }
                    }
                }) {
                    server.dispatchCommand(it.player, "mvtp ${it.player.name} world")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
            }
        }

        listen<PlayerJoinEvent> {
            it.player.inventory.setItem(0, navigator)
            it.player.inventory.setItem(8, ItemStack(Material.SPYGLASS))
            it.player.allowFlight = true
        }

        listen<PlayerChangedWorldEvent> {
            it.player.allowFlight = true
        }

        listen<InventoryClickEvent> {
            if (it.whoClicked.gameMode == GameMode.ADVENTURE) {
                if (!(it.clickedInventory == it.whoClicked.inventory && it.whoClicked.inventory.getItem(it.slot)?.type == Material.SPYGLASS)) {
                    it.isCancelled = true
                }
            }
        }

        listen<PlayerInteractEvent> {
            if (it.item?.type == Material.RECOVERY_COMPASS) {
                it.player.playSound(Sound.sound(Key.key("block.end_portal_frame.fill"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
                it.player.openGUI(navigatorGui)
            }
            if (it.item?.type == Material.FIREWORK_ROCKET) {
                taskRunLater(1, true) {
                    it.player.inventory.setItem(4, fireworkItemStack(64) { power = 3 })
                }
            }
        }

        listen<PlayerMoveEvent> {
            if (it.player.gameMode != GameMode.CREATIVE && it.player.world.name == "world" && it.to.y <= -3.0) {
                it.player.velocity = Vector(it.player.velocity.x, 1.0, it.player.velocity.z)
                it.player.playSound(Sound.sound(Key.key("entity.player.attack.sweep"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
            }
        }

        listen<FoodLevelChangeEvent> {
            it.isCancelled = true
        }

        listen<EntityDamageEvent> {
            it.isCancelled = true
        }

        fun elytra(player: Player) {
            if (player.inventory.chestplate?.type == Material.ELYTRA) {
                player.title(literalText(""), literalText("ᴅɪsᴀʙʟᴇᴅ ᴇʟʏᴛʀᴀ") { color = KColors.RED }, Duration.ZERO, Duration.ofMillis(500), Duration.ofMillis(500))
                player.inventory.chestplate = ItemStack(Material.AIR)
                player.inventory.setItem(4, ItemStack(Material.AIR))
            } else {
                player.title(literalText(""), literalText("ᴇɴᴀʙʟᴇᴅ ᴇʟʏᴛʀᴀ") { color = KColors.GREEN}, Duration.ZERO, Duration.ofMillis(500), Duration.ofMillis(500))
                player.playSound(Sound.sound(Key.key("entity.player.attack.sweep"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
                player.inventory.chestplate = itemStack(Material.ELYTRA) { meta { isUnbreakable = true } }
                player.inventory.setItem(4, fireworkItemStack(64) { power = 3 })
                player.velocity = Vector(player.velocity.x, 3.0, player.velocity.z)
                taskRunLater(5, true) {
                    player.isGliding = true
                }
            }
        }
        command("elytra") {
            runs { elytra(player) }
        }
        command("ely") {
            runs { elytra(player) }
        }
    }

    override fun shutdown() { }
}