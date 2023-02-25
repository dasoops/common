package com.dasoops.common.extension

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