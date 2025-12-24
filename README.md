# Vakya — Screenshot Intelligence (Cross-Platform Checklist)

> **Important constraint (locked):**
> ❌ No automatic screenshot detection
> ✅ User-driven screenshot import / share
> ✅ Same core logic on Android & iOS

This is the **only correct cross-platform design**.

---

## PHASE 0 — Define the Screenshot Flow (Lock This)

### Supported user actions

* User:

  * Shares a screenshot to Vakya
    **OR**
  * Picks screenshot from gallery inside Vakya

### Vakya does

1. Extract text (OCR)
2. Summarize content
3. Classify (Task / Idea / Note)
4. Extract date (optional)
5. Save as structured note

---

## PHASE 1 — Add Image Input Capability

### 1.1 Android

* [x] Add `ACTION_SEND` intent filter (image/*
* [x] Accept `content://` URI
* [x] Read bitmap via ContentResolver
* [x] Pass image bytes to shared pipeline

### 1.2 iOS

* [x] Add **Share Extension** (image only)
* [x] Receive `UIImage`
* [x] Convert to PNG/JPEG bytes
* [x] Pass bytes to shared pipeline

📌 **Do NOT OCR in platform code**

---

## PHASE 2 — Shared Screenshot Pipeline (commonMain)

Create this in `commonMain`:

```kotlin
interface ScreenshotProcessor {
    suspend fun process(
        imageBytes: ByteArray,
        createdAt: Long
    ): Note
}
```

Checklist:

* [ ] Define pipeline stages:

  * OCR
  * Cleanup
  * Summarization
  * Classification
  * Date extraction
* [ ] Output → `Note` model (already exists)

---

## PHASE 3 — OCR (Platform-Specific, API-Stable)

### 3.1 Android OCR

* [ ] Use **ML Kit Text Recognition (on-device)**
* [ ] Input: Bitmap
* [ ] Output: Raw text
* [ ] Pass text → commonMain

### 3.2 iOS OCR

* [ ] Use **Vision.framework (VNRecognizeTextRequest)**
* [ ] Input: UIImage
* [ ] Output: Raw text
* [ ] Pass text → commonMain

📌 OCR is the **only platform-divergent ML**

---

## PHASE 4 — Text Normalization (commonMain)

* [ ] Remove junk:

  * Status bar text
  * Repeated whitespace
* [ ] Preserve structure:

  * Newlines
  * Bullet points
* [ ] Reject OCR if:

  * < 10 characters
  * Garbage confidence

---

## PHASE 5 — Screenshot Summarization (commonMain)

### Phase-1 (Rule-Based)

* [ ] Take top meaningful lines
* [ ] Max 3 bullets
* [ ] Max 60 words
* [ ] Ignore:

  * App chrome
  * Repeated UI text

Later:

* Swap with on-device LLM

---

## PHASE 6 — Classification (Reuse Your Existing Models)

You already have:

* `ClassificationInput`
* `ClassificationOutput`
* `NoteType`

Checklist:

* [ ] Feed OCR text
* [ ] Predict:

  * Task → has date / imperative verb
  * Idea → exploratory language
  * Note → default
* [ ] Confidence score

---

## PHASE 7 — Date & Event Extraction

* [ ] Regex-based extraction:

  * Today / Tomorrow
  * dd/mm/yyyy
  * “by Friday”
* [ ] Normalize timestamp
* [ ] Store as `targetDate`

---

## PHASE 8 — User Confirmation UI (Critical)

Before saving:

* [ ] Show:

  * Screenshot preview
  * Summary
  * Detected type
  * Detected date
* [ ] Allow:

  * Edit text
  * Change type
  * Change bucket
* [ ] Confirm → save

This prevents junk notes.

---

## PHASE 9 — Persistence

* [ ] Save note via repository
* [ ] Store:

  * OCR text
  * Summary
  * Screenshot URI (platform-local)
* [ ] Do NOT duplicate image bytes

---

## PHASE 10 — Screens Integration

### Notes Screen

* [ ] Screenshot badge/icon
* [ ] Tap → show image + extracted content

### Buckets Screen

* [ ] Filter screenshot-based notes

---

## PHASE 11 — Permissions Checklist

### Android

* [ ] READ_MEDIA_IMAGES (Android 13+)
* [ ] No background permissions
* [ ] No internet permission

### iOS

* [ ] Photo Library usage description
* [ ] Share extension entitlement

---

## PHASE 12 — What This Gives You (Important)

You can confidently say:

> “Vakya processes screenshots **fully offline**, extracts meaning, and converts them into structured tasks or notes — on both Android and iOS.”

That is **portfolio-grade**.

---

## What You Must NOT Attempt (Yet)

❌ Auto screenshot detection
❌ Background listeners
❌ Calendar auto-add
❌ Always-on services

Those break iOS parity.

---

## Recommended Next Concrete Step (Do This Now)

👉 **Implement Phase 1 + Phase 2 only**

* Screenshot share/import
* Stub OCR (return fake text)
* Full pipeline wiring

Once that works, OCR drops in cleanly.

---
