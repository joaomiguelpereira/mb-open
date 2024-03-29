package com.medibooking.admin.client.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.layout.client.Layout.Layer;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

//Copy from sliding panel from expenses samples
public class SlidingPanel extends ResizeComposite implements HasOneWidget,
		HasWidgets {

	private final List<Widget> widgets = new ArrayList<Widget>();
	private final LayoutPanel layoutPanel = new LayoutPanel();

	private int currentIndex = -1;

	private int animationSpeed = 500;

	public SlidingPanel() {
		initWidget(layoutPanel);

	}

	public void add(IsWidget w) {
		add(asWidgetOrNull(w.asWidget()));
	}

	public void add(Widget w) {
		widgets.remove(w);
		widgets.add(w);

		// Display the first widget added by default
		if (currentIndex < 0) {
			layoutPanel.add(w);
			currentIndex = 0;
		}
	}

	public void clear() {
		setWidget(null);
		widgets.clear();
	}

	public Widget getWidget() {
		return widgets.get(currentIndex);
	}

	public Iterator<Widget> iterator() {
		return Collections.unmodifiableList(widgets).iterator();
	}

	public boolean remove(Widget w) {
		return widgets.remove(w);
	}

	public void setWidget(IsWidget w) {
		setWidget(asWidgetOrNull(w));
	}

	/**
	 * Set the widget to show, adding it to the end of our sliding set if we
	 * haven't seen it before. Nulls are ignored.
	 */
	// Conflict btw deprecated Composite#setWidget and HasOneWidget#setWidget
	@SuppressWarnings("deprecation")
	public void setWidget(Widget widget) {
		if (widget == null) {
			return;
		}

		int newIndex = widgets.indexOf(widget);

		if (newIndex < 0) {
			newIndex = widgets.size();
			add(widget);
		}

		show(newIndex);
	}

	private void show(int newIndex) {
		if (newIndex == currentIndex) {
			return;
		}

		boolean fromLeft = newIndex < currentIndex;
		currentIndex = newIndex;

		Widget widget = widgets.get(newIndex);
		final Widget current = layoutPanel.getWidget(0);

		// Initialize the layout.
		layoutPanel.add(widget);
		layoutPanel.setWidgetLeftWidth(current, 0, Unit.PCT, 100, Unit.PCT);
		if (fromLeft) {
			layoutPanel.setWidgetLeftWidth(widget, -100, Unit.PCT, 100,
					Unit.PCT);
		} else {
			layoutPanel
					.setWidgetLeftWidth(widget, 100, Unit.PCT, 100, Unit.PCT);
		}
		layoutPanel.forceLayout();

		// Slide into view.
		if (fromLeft) {
			layoutPanel.setWidgetLeftWidth(current, 100, Unit.PCT, 100,
					Unit.PCT);
		} else {
			layoutPanel.setWidgetLeftWidth(current, -100, Unit.PCT, 100,
					Unit.PCT);
		}
		layoutPanel.setWidgetLeftWidth(widget, 0, Unit.PCT, 100, Unit.PCT);
		layoutPanel.animate(animationSpeed, new Layout.AnimationCallback() {
			public void onAnimationComplete() {
				// Remove the old widget when the animation completes.
				layoutPanel.remove(current);
			}

			public void onLayout(Layer layer, double progress) {
			}
		});
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	public int getAnimationSpeed() {
		return animationSpeed;
	}

}
