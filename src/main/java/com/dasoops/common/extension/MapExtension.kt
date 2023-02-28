package com.dasoops.common.extension

import java.util.stream.Collectors

inline fun <K, V, KR, VR> Map<out K, V>.mapTo(
    keyTransform: (Map.Entry<K, V>) -> KR,
    valueTransform: (Map.Entry<K, V>) -> VR
): Map<KR, VR> {
    return mapTo(LinkedHashMap(this.size), keyTransform, valueTransform) // .optimizeReadOnlyMap()
}

inline fun <K, V, KR, VR, M : MutableMap<in KR, in VR>> Map<out K, V>.mapTo(
    destination: M,
    keyTransform: (Map.Entry<K, V>) -> KR,
    valueTransform: (Map.Entry<K, V>) -> VR
): M {
    return entries.associateByTo(destination, keyTransform, valueTransform)
}

fun <T, K, V> Collection<T>.toMap(
    keyTransform: (T) -> K,
    valueTransform: (T) -> V
): MutableMap<K, V> {
    return stream().collect(Collectors.toMap(keyTransform, valueTransform))
}