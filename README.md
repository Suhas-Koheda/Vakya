# Vakya — Screenshot Intelligence (Cross-Platform Checklist)

Vakya is a cross-platform application for Android and iOS that intelligently processes screenshots. Users can share or import screenshots into the app, which then extracts text, summarizes the content, classifies it as a task, idea, or note, and optionally extracts a date. This structured information is then saved as a note, turning ephemeral screenshots into actionable information. The core logic is shared between platforms, with platform-specific implementations for UI and screenshot import.

> **Important constraint (locked):**
> ❌ No automatic screenshot detection
> ✅ User-driven screenshot import / share
> ✅ Same core logic on Android & iOS
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

## Vakya — Screenshot Intelligence (Updated Checklist)

### PHASE 0 — Design constraints (LOCKED)

* [x] No automatic screenshot detection
* [x] User-driven import / share only
* [x] Fully offline processing
* [x] Identical core logic on Android & iOS

---

## PHASE 1 — Image ingestion

### Android

* [x] `ACTION_SEND` intent filter (image/*)
* [x] Receive `content://` URI
* [x] Read image via `ContentResolver`
* [x] Convert to in-memory bytes only

### iOS

* [x] Share extension (UIImage input)
* [x] Convert to PNG/JPEG bytes
* [x] Pass to shared pipeline

---

## PHASE 2 — Screenshot pipeline (commonMain)

* [x] `ScreenshotProcessor` abstraction
* [x] Platform OCR via `OcrEngine`
* [x] LLM-based interpretation
* [x] Structured extraction model
* [x] Suggested actions inference

---

## PHASE 3 — OCR (platform-specific)

### Android

* [x] ML Kit Text Recognition
* [ ] Image downscaling before OCR (optimization)

### iOS

* [ ] Vision.framework OCR

---

## PHASE 4 — Text normalization

* [ ] Remove status bar / junk text
* [ ] Normalize whitespace
* [ ] Reject garbage OCR
* [ ] Limit max lines before LLM

---

## PHASE 5 — Screenshot interpretation

* [x] Summary extraction
* [x] Link extraction
* [x] Question extraction
* [x] Todo extraction
* [ ] Replace string parsing with structured interpreter

---

## PHASE 6 — User intent layer (CRITICAL)

* [x] `ScreenshotAction` sealed model
* [x] Suggested actions generation
* [ ] Rank actions by relevance
* [ ] Multi-select actions

---

## PHASE 7 — Confirmation UI

* [x] Preview summary
* [ ] Preview image
* [ ] Allow text clipping
* [ ] Allow action selection
* [ ] Choose storage policy

---

## PHASE 8 — Note creation (explicit only)

* [ ] Create `Note` from user-selected content
* [ ] Attach `TextClip`s
* [ ] Assign bucket
* [ ] Assign type
* [ ] Assign date (if detected)

🚫 Never auto-create notes

---

## PHASE 9 — Image storage

* [ ] Implement `ScreenshotStorage` (expect/actual)
* [ ] Support:

  * DISCARD
  * KEEP_LOCALLY
  * ATTACH_TO_NOTE
* [ ] Persist only after confirmation

---

## PHASE 10 — Persistence & browsing

* [ ] Notes repository
* [ ] Screenshot badge in notes list
* [ ] Screenshot preview screen
* [ ] Filter screenshot-based notes

---

## PHASE 11 — Optimizations

* [ ] Remove raw `ByteArray` from StateFlow
* [ ] Deterministic ID & time providers
* [ ] OCR image scaling
* [ ] Structured LLM output
* [ ] Field-level confidence scores

---

## PHASE 12 — Explicit non-goals (for now)

* ❌ Auto screenshot detection
* ❌ Background listeners
* ❌ Calendar auto-insertion
* ❌ Cloud AI
* ❌ Always-on services
